package com.justai.jaicf.regression.scenario.telephony

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.telephony

/*
* Проверка баржина в контексте
* Ожидаемое поведение - прерываемся на "да" и "нет"
* */
val telephonyBargeInContextScenario = Scenario(telephony) {

    state("bargeIn") {
        activators {
            regex("говори")
        }

        action {
            reactions.say(
                "длинный текст который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго",
                bargeInContext = "/Nested",
            )
        }
    }

    state("Nested", modal = true) {
        state("Yes") {
            activators {
                regex("да")
            }
            action {
                reactions.say("да")
            }
        }

        state("No") {
            activators {
                regex("нет")
            }
            action {
                reactions.say("Нет")
            }
        }
    }

    state("TopLevel"){
        activators {
            regex("корень")
        }
        action {
            reactions.say("Корень")
        }
    }

    fallback {
        reactions.say("fallback")
    }
}