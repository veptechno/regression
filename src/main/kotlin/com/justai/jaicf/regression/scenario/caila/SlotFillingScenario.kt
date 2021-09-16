package com.justai.jaicf.regression.scenario.caila

import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.builder.Scenario

/*
* CAILA Project is available at ./static/caila/caila_import.json
* Import this project to CAILA and run testing scenario
* Or put your caila access token to JAICP_API_TOKEN environmental variable and run autotest at src/test/kotlin/.../CailaFunctionalTest.kt
* */
val SlotFillingRegressionScenario = Scenario {
    state("TestSlotFilling") {
        activators {
            intent("TestSlotFilling")
        }
        action {
            reactions.say("Test OK with slots: ${activator.caila?.slots}")
        }
    }
    state("TestSlotFillingInterruption") {
        activators {
            intent("TestSlotFillingInterruption")
        }
        action {
            reactions.say("Interrupt OK")
        }
    }
}
