package com.justai.jaicf.template.scenario.features

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.dto.jaicpAnalytics
import com.justai.jaicf.channel.jaicp.reactions.jaicp

val AnalyticsFeatureScenario = Scenario {
    state("start") {
        activators {
            regex(".start")
        }
        action {
            reactions.say("Hello!")
            reactions.jaicp?.startNewSession()
        }
    }
    state("setMessageLabel") {
        activators {
            regex("setMessageLabel")
        }
        action {
            reactions.say("Setting message label `labelName` with group `groupName`")
            reactions.say("Make sure you added label and group in Labels settings in JAICP")
            jaicpAnalytics.setMessageLabel("labelName", "groupName")
        }
    }
    state("setResult") {
        activators {
            regex("setResult")
        }
        action {
            reactions.say("Setting session result `OK`")
            jaicpAnalytics.setSessionResult("OK")
        }
    }
    state("setSessionLabel") {
        activators {
            regex("setSessionLabel")
        }
        action {
            reactions.say("Setting session label `someLabel`.")
            reactions.say("Make sure you added session label in Labels settings in JAICP")
            jaicpAnalytics.setSessionLabel("говно")
        }
    }
    state("setSessionData") {
        activators {
            regex("setSessionData")
        }
        action {
            reactions.say("Setting sessionData")
            jaicpAnalytics.setSessionData("header", "value")
        }
    }
    state("setComment") {
        activators {
            regex("setComment")
        }
        action {
            reactions.say("Setting phrase comment")
            jaicpAnalytics.setComment("This works.")
        }
    }
}