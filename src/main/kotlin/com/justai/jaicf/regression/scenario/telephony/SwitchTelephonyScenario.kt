package com.justai.jaicf.regression.scenario.telephony

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.channels.TelephonyEvents.BARGE_IN_EVENT
import com.justai.jaicf.channel.jaicp.telephony

val switchTelephoneScenario = Scenario(telephony) {

    state("switch") {
        activators {
            regex("перевод")
        }

        action {
            reactions.transferCall("79006535228")
        }
    }

}
