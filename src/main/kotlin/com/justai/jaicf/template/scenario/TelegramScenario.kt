package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.telegram.*
import com.justai.jaicf.channel.viber.api.ViberEvent
import com.justai.jaicf.channel.viber.contact
import com.justai.jaicf.channel.viber.image
import com.justai.jaicf.channel.viber.location
import com.justai.jaicf.channel.viber.sticker
import com.justai.jaicf.channel.viber.viber
import com.justai.jaicf.channel.viber.video
import com.justai.jaicf.plugin.PathValue
import com.justai.jaicf.plugin.UsesReaction

val telegramScenario = Scenario(telegram) {

    state("test") {
        activators {
            regex("test")
        }

        action {
            reactions.say("Это начало тестирования реакций. Убедитесь что последнее сообщение \"Конец тестирования\"")

            reactions.say("Картинка:")
            reactions.image("https://i.ytimg.com/vi/8W2njNW6hI0/hqdefault.jpg")

            reactions.say("Картинка с описанием:")
            reactions.image("https://i.ytimg.com/vi/8W2njNW6hI0/hqdefault.jpg", "Это описание")

            reactions.say("PDF файл:")
            reactions.sendDocument("https://www.clickdimensions.com/links/TestPDFfile.pdf")

            reactions.say("Точка на карте:")
            reactions.sendLocation(60.0f, 30.0f)

            reactions.say("Ролик:")
            reactions.sendVideo("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4")

            reactions.say("Аудио:")
            reactions.audio("https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3")

            reactions.say("Кнопки:")
            reactions.buttons("1", "2", "3")

            reactions.say("Конец тестирования")
            reactions.go("../input_test")
        }
    }

    state("input_test") {
        val uncheckedEvents = mutableListOf(
            TelegramEvent.LOCATION,
            TelegramEvent.DOCUMENT,
            TelegramEvent.PHOTOS,
            TelegramEvent.STICKER,
            TelegramEvent.VIDEO,
            TelegramEvent.VOICE,
        )

        action {
            reactions.say("Теперь протестируем получение сообщений")
            reactions.say("Вам нужно отправить ${uncheckedEvents.joinToString()}")
        }

        state("location") {
            activators {
                event(TelegramEvent.LOCATION)
            }

            action(telegram.location) {
                reactions.say("Вы отправили геолокацию с координатами ${request.location.latitude}:${request.location.longitude}")

                reactions.removeAndCheck(TelegramEvent.LOCATION, uncheckedEvents)
            }
        }

        state("contact") {
            activators {
                event(TelegramEvent.CONTACT)
            }

            action(telegram.contact) {
                reactions.say("Вы отправили контакт ${request.contact}")

                reactions.removeAndCheck(TelegramEvent.CONTACT, uncheckedEvents)
            }
        }

        state("file", noContext = true) {
            activators {
                event(TelegramEvent.DOCUMENT)
            }

            action(telegram.document) {
                reactions.say("Вы отправили документ/файл с именем ${request.document.fileName}")

                reactions.removeAndCheck(TelegramEvent.DOCUMENT, uncheckedEvents)
            }
        }

        state("photo", noContext = true) {
            activators {
                event(TelegramEvent.PHOTOS)
            }

            action(telegram.photos) {
                reactions.say("Вы отправили фото ${request.photos}")

                reactions.removeAndCheck(TelegramEvent.PHOTOS, uncheckedEvents)
            }
        }

        state("sticker", noContext = true) {
            activators {
                event(TelegramEvent.STICKER)
            }

            action(telegram.sticker) {
                reactions.say("Вы отправили стикер ${request.sticker}")

                reactions.removeAndCheck(TelegramEvent.STICKER, uncheckedEvents)
            }
        }

        state("video", noContext = true) {
            activators {
                event(TelegramEvent.VIDEO)
            }

            action(telegram.video) {
                reactions.say("Вы отправили видео ${request.video}")

                reactions.removeAndCheck(TelegramEvent.VIDEO, uncheckedEvents)
            }
        }

        state("voice", noContext = true) {
            activators {
                event(TelegramEvent.VOICE)
            }

            action(telegram.voice) {
                reactions.say("Вы отправили голосовое ${request.voice}")

                reactions.removeAndCheck(TelegramEvent.VOICE, uncheckedEvents)
            }
        }
    }

    state("end") {
        action {
            reactions.say("Тестирование окончено")
        }
    }

    fallback {
        reactions.say("Вы написали ${request.message.text}. Для тестирования напишите test")
    }
}

@UsesReaction("say")
private fun TelegramReactions.removeAndCheck(
    event: String,
    uncheckedEvents: MutableList<String>,
    @PathValue end: String = "../../end"
) {
    uncheckedEvents -= event
    if (uncheckedEvents.isNotEmpty())
        say("Вам осталось отправить ${uncheckedEvents.joinToString()}")
    else
        go(end)
}