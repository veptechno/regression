package com.justai.jaicf.regression.scenario.features.routing

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.api.routing.BotRoutingEngine
import com.justai.jaicf.api.routing.routing
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.model.scenario.Scenario

/**
 * This is Functional Test for BotRouting
 * */

private fun createEngine(scenario: Scenario) = BotEngine(scenario, activators = arrayOf(RegexActivator))

private val main = Scenario {
    state("route sc2 and back") {
        activators {
            regex("route sc2 and back")
        }
        action {
            routing.route("sc2")
        }
    }

    state("route") {
        activators {
            regex("route .*")
        }
        action {
            val target = request.input.replace("route ", "")
            reactions.say("Routing current request to $target")
            routing.route(target)
        }
    }

    state("changeBot") {
        activators {
            regex("changeBot .*")
        }
        action {
            val target = request.input.replace("changeBot ", "")
            reactions.say("Changing bot to $target")
            routing.changeEngine(target)
        }
    }

    fallback {
        reactions.say("MAIN: Fallback")
    }
}

private val sc1 = Scenario {
    state("routeBack") {
        activators {
            regex("routeBack")
        }
        action {
            reactions.say("routing back")
            routing.routeBack()
        }
    }

    state("changeBotBack") {
        activators {
            regex("changeBotBack")
        }

        action {
            reactions.say("changing bot back")
            routing.changeEngineBack()
        }
    }

    state("changeBot") {
        activators {
            regex("changeBot .*")
        }
        action {
            val target = request.input.replace("changeBot ", "")
            reactions.say("Changing bot to $target")
            routing.changeEngine(target)
        }
    }

    fallback {
        reactions.say("SC1: Fallback")
    }
}

private val sc2 = Scenario {
    state("changeBotBack") {
        activators {
            regex("changeBotBack")
        }

        action {
            reactions.say("changing bot back")
            routing.changeEngineBack()
        }
    }

    fallback {
        reactions.say("SC2: Fallback")
    }
}

val RoutingEngineFeatureBot = BotRoutingEngine(
    "main" to createEngine(main),
    mapOf("sc1" to createEngine(sc1), "sc2" to createEngine(sc2))
)
