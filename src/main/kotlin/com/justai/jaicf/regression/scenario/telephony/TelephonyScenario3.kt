package com.justai.jaicf.regression.scenario.telephony

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.dto.bargein.BargeInMode
import com.justai.jaicf.channel.jaicp.dto.bargein.BargeInTrigger
import com.justai.jaicf.channel.jaicp.telephony

val telephonyScenario3 = Scenario(telephony) {

    state("bargeIn") {
        activators {
            regex("говори")
        }

        action {
            reactions.say(
                "длинный текст который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго",
                bargeIn = true
            )
        }
    }

    state("bargeInYes") {
        activators {
            regex("да")
        }

        action {
            reactions.say(
                "длинный текст который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго",
                bargeIn = true,

            )
            reactions.say("sdsd")
        }
    }

    state("yes") {
        activators {
            regex("нет")
        }

        action {
            reactions.say("Прерывание в стейте ${this.context.dialogContext.currentState}")
        }
    }

    fallback {
        reactions.say("fallback")
    }
}