package com.justai.jaicf.regression.scenario.telephony

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.dto.bargein.BargeInMode
import com.justai.jaicf.channel.jaicp.dto.bargein.BargeInTrigger
import com.justai.jaicf.channel.jaicp.telephony

/*
* Проверка баржина вместе с noInterruptTime
* Ожидаемое поведение - прерываемся на "да" спустя 5с речи бота, все остальное игнорируем.
* */
val telephonyScenario2 = Scenario(telephony) {

    state("bargeIn") {
        activators {
            regex("говори")
        }

        action {
            reactions.bargeIn(
                BargeInMode.FORCED,
                BargeInTrigger.FINAL,
                5000
            )
            reactions.say(
                "длинный текст который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго который долго",
                bargeIn = true
            )
        }
    }

    state("yes") {
        activators {
            regex("да")
        }

        action {
            reactions.say("Прерывание в стейте ${this.context.dialogContext.currentState}")
        }
    }

    fallback {
        reactions.say("fallback")
    }
}