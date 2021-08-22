package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.chatwidget
import com.justai.jaicf.channel.telegram.*

val chatWidgetScenario = Scenario(chatwidget) {

    state("test") {
        activators {
            regex("test")
        }

        action {
            reactions.say("Это начало тестирования реакций. Убедитесь что последнее сообщение \"Конец тестирования\"")

            reactions.say("Картинка:")
            reactions.image("https://i.ytimg.com/vi/8W2njNW6hI0/hqdefault.jpg")

            reactions.say("Картинка с подписью:")
            reactions.image("https://i.ytimg.com/vi/8W2njNW6hI0/hqdefault.jpg", "Это подпись")

            reactions.say("Конец тестирования")
            reactions.go("../input_test")
        }
    }

    state("input_test") {
        action {
            reactions.say("Нажмите на любую кнопку, бот должен написать какую кнопку вы выбрали")
            reactions.buttons("1", "2", "3")
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