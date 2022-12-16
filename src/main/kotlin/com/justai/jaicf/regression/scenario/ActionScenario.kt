package com.justai.jaicf.regression.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.googleactions.actions

val actionsScenario = Scenario(actions) {

    state("picture") {
        activators {
            regex("картинка")
        }

        action {
            reactions.image("https://media.giphy.com/media/ICOgUNjpvO0PC/source.gif")
        }
    }

    state("buttons") {
        activators {
            regex("кнопки")
        }

        action {
            reactions.buttons("1", "2", "3")
            reactions.go("one")
            reactions.go("one")
        }

        state("one") {
            activators { regex("1") }
            action { reactions.say("You selected 1") }
        }
    }



    state("audio") {
        activators {
            regex("аудио")
        }

        action {
            reactions.playAudio("https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3")
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
