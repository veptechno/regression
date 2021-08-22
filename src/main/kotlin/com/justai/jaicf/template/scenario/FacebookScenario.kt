package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.facebook.api.CarouselElement
import com.justai.jaicf.channel.facebook.facebook
import com.justai.jaicf.channel.viber.viber
import java.net.URL

val facebookScenario = Scenario(facebook) {

    state("test") {
        activators {
            regex("test")
        }

        action {
            reactions.say("Это начало тестирования реакций. Убедитесь что последнее сообщение \"Конец тестирования\"")

            reactions.say("Картинка:")
            reactions.image("https://i.ytimg.com/vi/8W2njNW6hI0/hqdefault.jpg")

            reactions.say("Картинка с описанием:")
            reactions.image("https://i.ytimg.com/vi/8W2njNW6hI0/hqdefault.jpg")

            reactions.say("PDF файл:")
            reactions.file("https://www.clickdimensions.com/links/TestPDFfile.pdf")

            reactions.say("Видео в видеоплеере:")
            reactions.video("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4")

            reactions.say("Кнопки с title:")
            reactions.buttons("title", listOf("1", "2"))

            reactions.say("Аудио:")
            reactions.audio("https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3")

            reactions.say("Карусель:")
            reactions.carousel(
                CarouselElement(
                    "title1",
                    "subtitle1",
                    URL("https://p1.zoon.ru/preview/nT8_s6xSaDEyjaBARdqrgw/504x270x85/1/4/b/original_52568bef40c08891208bc999_5c0a2f76d23b1.jpg")
                ),
                CarouselElement(
                    "title2",
                    "subtitle2",
                    URL("https://static.tildacdn.com/tild3262-6461-4161-b339-643136336265/7393505506535bbfc4e5.jpg")
                )
            )

            reactions.say("Конец тестирования")
            reactions.go("../input_test")
        }
    }

    state("input_test") {
        action {
            reactions.say("Нажмите на любую кнопку, бот должен написать какую кнопку вы выбрали")
            reactions.buttons("Выберете кнопку", listOf("1", "2", "3"))
        }

        state("1") {
            activators { regex("1") }
            action {
                reactions.say("1")
                reactions.go("../../end")
            }
        }

        state("2") {
            activators { regex("2") }
            action {
                reactions.say("2")
                reactions.go("../../end")
            }
        }

        state("3") {
            activators { regex("3") }
            action {
                reactions.say("3")
                reactions.go("../../end")
            }
        }
    }

    state("end") {
        action {
            reactions.say("Тестирование окончено")
        }
    }

    fallback {
        reactions.say("Вы написали ${request.input}. Для тестирования напишите test")
    }
}