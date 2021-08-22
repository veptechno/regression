package com.justai.jaicf.template.scenario

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.event.event
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.viber.api.CarouselElement
import com.justai.jaicf.channel.viber.api.ViberEvent
import com.justai.jaicf.channel.viber.api.ViberEvent.CONTACT_MESSAGE
import com.justai.jaicf.channel.viber.api.ViberEvent.FILE_MESSAGE
import com.justai.jaicf.channel.viber.api.ViberEvent.IMAGE_MESSAGE
import com.justai.jaicf.channel.viber.api.ViberEvent.LOCATION_MESSAGE
import com.justai.jaicf.channel.viber.api.ViberEvent.STICKER_MESSAGE
import com.justai.jaicf.channel.viber.api.ViberEvent.VIDEO_MESSAGE
import com.justai.jaicf.channel.viber.api.isReceivedAsSilent
import com.justai.jaicf.channel.viber.contact
import com.justai.jaicf.channel.viber.conversationStarted
import com.justai.jaicf.channel.viber.file
import com.justai.jaicf.channel.viber.image
import com.justai.jaicf.channel.viber.location
import com.justai.jaicf.channel.viber.sdk.api.KeyboardBuilder
import com.justai.jaicf.channel.viber.sdk.api.OpenMapButton
import com.justai.jaicf.channel.viber.sdk.api.RedirectButton
import com.justai.jaicf.channel.viber.sdk.api.ReplyButton
import com.justai.jaicf.channel.viber.sdk.api.ViberButton
import com.justai.jaicf.channel.viber.sdk.api.toKeyboard
import com.justai.jaicf.channel.viber.sdk.message.Location
import com.justai.jaicf.channel.viber.sdk.message.PictureMessage
import com.justai.jaicf.channel.viber.sdk.message.TextMessage
import com.justai.jaicf.channel.viber.sticker
import com.justai.jaicf.channel.viber.subscribed
import com.justai.jaicf.channel.viber.text
import com.justai.jaicf.channel.viber.viber
import com.justai.jaicf.channel.viber.video
import java.io.File

val viberScenario = Scenario(viber) {

    state("new_conversation") {
        activators {
            event(ViberEvent.CONVERSATION_STARTED)
        }

        action(viber.conversationStarted) {
            reactions.say("CONVERSATION_STARTED event received")
        }
    }

    state("subscribed") {
        activators {
            event(ViberEvent.SUBSCRIBED)
        }

        action(viber.subscribed) {
            reactions.say("SUBSCRIBED event received")
        }
    }

    state("test") {
        activators {
            regex("test")
        }

        action(viber.text) {
            reactions.say("Это начало тестирования реакций. Убедитесь что последнее сообщение \"Конец тестирования\"")

            reactions.say("Отправитель (то есть вы):")
            reactions.say(request.event.sender)

            reactions.say("Картинка:")
            reactions.image("https://i.ytimg.com/vi/8W2njNW6hI0/hqdefault.jpg")

            reactions.say("Картинка с описанием:")
            reactions.image("https://i.ytimg.com/vi/8W2njNW6hI0/hqdefault.jpg", "This is description")

            reactions.say("PDF файл:")
            reactions.file("https://www.clickdimensions.com/links/TestPDFfile.pdf", "TestPDFfile", "pdf")

            reactions.say("Точка на карте:")
            reactions.location(60.0, 30.0)

            reactions.say("Стикер:")
            reactions.sticker(40112)

            reactions.say("Ролик на ютуб ролик:")
            reactions.url("https://www.youtube.com/watch?v=gxSjV-XMBVE")

            reactions.say("Видео в видеоплеере:")
            reactions.video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4")

            reactions.say("Карусель:")
            reactions.carousel(
                CarouselElement(
                    "https://p1.zoon.ru/preview/nT8_s6xSaDEyjaBARdqrgw/504x270x85/1/4/b/original_52568bef40c08891208bc999_5c0a2f76d23b1.jpg",
                    "First mall title",
                    "Subtitle for first mall",
                    ReplyButton("Reply button")
                ),
                CarouselElement(
                    "https://static.tildacdn.com/tild3262-6461-4161-b339-643136336265/7393505506535bbfc4e5.jpg",
                    "Title of second shop"
                ),
                CarouselElement(
                    "https://40.img.avito.st/640x480/9202090740.jpg",
                    "Third title",
                    button = RedirectButton("Redirect button", redirectUrl = "https://5ka.ru/?100try.com")
                )
            )

            reactions.say("Инлайн кнопки:")
            reactions.inlineButtons {
                row("1", "2", "3")
                row("4")
            }

            val viberKeyboard = KeyboardBuilder(ViberButton.Style(backgroundColor = "#fdebd0")).apply {
                row("1", "2", "3")
                row("4")
            }.build()
            reactions.sendMessage(
                TextMessage(
                    "Клавиатура:",
                    keyboard = viberKeyboard.toKeyboard()
                )
            )

            reactions.say("Конец тестирования")
            reactions.go("../input_test")
        }
    }

    state("input_test") {
        val uncheckedEvents = mutableListOf(
            FILE_MESSAGE,
            IMAGE_MESSAGE,
            VIDEO_MESSAGE,
            LOCATION_MESSAGE,
            STICKER_MESSAGE,
            CONTACT_MESSAGE
        )

        action {
            reactions.say("Теперь протестируем получение сообщений. Все сообщений (кроме контакта) должны отвечать эхом")
            reactions.say("Вам нужно отправить ${uncheckedEvents.joinToString()}")
        }

        state("file", noContext = true) {
            activators {
                event(FILE_MESSAGE)
            }

            action(viber.file) {
                reactions.say("Вы отправили файл")
                val fileMessage = request.message
                reactions.file(fileMessage.url, File(fileMessage.filename))

                uncheckedEvents -= FILE_MESSAGE
                if (uncheckedEvents.isNotEmpty())
                    reactions.say("Вам осталось отправить ${uncheckedEvents.joinToString()}")
                else
                    reactions.go("../../end")
            }
        }

        state("image", noContext = true) {
            activators {
                event(IMAGE_MESSAGE)
            }

            action(viber.image) {
                reactions.say("Вы отправили картинку")
                reactions.image(request.message.url)

                uncheckedEvents -= IMAGE_MESSAGE
                if (uncheckedEvents.isNotEmpty())
                    reactions.say("Вам осталось отправить ${uncheckedEvents.joinToString()}")
                else
                    reactions.go("../../end")
            }
        }

        state("video", noContext = true) {
            activators {
                event(VIDEO_MESSAGE)
            }

            action(viber.video) {
                reactions.say("Вы отправили видео")
                reactions.video(request.message.url)

                uncheckedEvents -= VIDEO_MESSAGE
                if (uncheckedEvents.isNotEmpty())
                    reactions.say("Вам осталось отправить ${uncheckedEvents.joinToString()}")
                else
                    reactions.go("../../end")
            }
        }

        state("location", noContext = true) {
            activators {
                event(LOCATION_MESSAGE)
            }

            action(viber.location) {
                reactions.say("Вы отправили геолокацию")
                val (latitude, longitude) = request.message.location
                reactions.location(latitude, longitude)

                uncheckedEvents -= LOCATION_MESSAGE
                if (uncheckedEvents.isNotEmpty())
                    reactions.say("Вам осталось отправить ${uncheckedEvents.joinToString()}")
                else
                    reactions.go("../../end")
            }
        }

        state("sticker", noContext = true) {
            activators {
                event(STICKER_MESSAGE)
            }

            action(viber.sticker) {
                reactions.say("Вы отправили стикер")
                reactions.sticker(request.message.stickerId)

                uncheckedEvents -= STICKER_MESSAGE
                if (uncheckedEvents.isNotEmpty())
                    reactions.say("Вам осталось отправить ${uncheckedEvents.joinToString()}")
                else
                    reactions.go("../../end")
            }
        }

        state("contact", noContext = true) {
            activators {
                event(CONTACT_MESSAGE)
            }

            action(viber.contact) {
                reactions.say("Вы отправили контакт ${request.message.contact.name}")

                uncheckedEvents -= CONTACT_MESSAGE
                if (uncheckedEvents.isNotEmpty())
                    reactions.say("Вам осталось отправить ${uncheckedEvents.joinToString()}")
                else
                    reactions.go("../../end")
            }
        }
    }

    state("end") {
        action(viber) {
            reactions.say("Тестирование окончено")
        }
    }

    fallback {
        viber.text {
            if (request.isReceivedAsSilent) {
                return@text
            }

            viber.text {
                reactions.say("Вы написали ${request.message.text}. Для тестирования напишите test")
            }
        }
    }
}