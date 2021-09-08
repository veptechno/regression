package com.justai.jaicf.template.scenario.features

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.dto.jaicpAnalytics
import com.justai.jaicf.channel.jaicp.reactions.jaicp

val AnalyticsFeatureScenario = Scenario {
    state("setMessageLabel") {
        activators {
            regex("setMessageLabel")
        }
        action {
            reactions.say("Setting message label `labelName` with group `groupName`")
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
            intent("setSessionLabel")
        }
        action {
            reactions.say("Setting session label `someLabel`.")
            jaicpAnalytics.setSessionLabel("someLabel")
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