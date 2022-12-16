package com.justai.jaicf.regression.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.googleactions.actions

val dialogflowScenario = Scenario(actions) {

    fallback {
        reactions.say("Этот ответ необходимо увидеть в dialogflow")
    }
}
