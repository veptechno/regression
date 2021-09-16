package com.justai.jaicf.regression.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.alexa.alexa
import com.justai.jaicf.channel.alexa.model.AlexaIntent.FALLBACK

val alexaScenario = Scenario(alexa) {

    state("test") {
        activators {
            intent(FALLBACK)
        }

        action {
            reactions.say("Это начало тестирования реакций. Убедитесь что последнее сообщение \"Конец тестирования\"")

            reactions.say("Аудио:")
            reactions.audio("https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3") // не пашет

            reactions.say("Конец тестирования")
            reactions.go("../input_test")
        }
    }

    state("input_test") {

        action {
            reactions.say("Теперь протестируем получение сообщений")
            reactions.say("Напишите привет") // там всё на интентах, не отработает
        }

        state("hi") {
            activators {
                regex("привет")
            }

            action {
                reactions.go("../../end")
            }
        }

        fallback {
            reactions.say("Вы написали ${request.input}. Напишите привет")
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
