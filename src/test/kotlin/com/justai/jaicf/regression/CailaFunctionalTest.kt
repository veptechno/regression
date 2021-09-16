package com.justai.jaicf.regression

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.caila.CailaIntentActivator
import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.activator.caila.slotfilling.CailaSlotFillingSettings
import com.justai.jaicf.regression.scenario.caila.SlotFillingRegressionScenario
import com.justai.jaicf.test.BotTest
import org.junit.jupiter.api.Test

private val SlotFillingRegressionBot = BotEngine(
    scenario = SlotFillingRegressionScenario,
    activators = arrayOf(CailaIntentActivator.Factory(CailaNLUSettings(accessToken,
        cailaSlotFillingSettings = CailaSlotFillingSettings(
            maxSlotRetries = 2,
            stopOnAnyIntent = true,
            stopOnAnyIntentThreshold = 0.4
        )))
    )
)

class CailaFunctionalTest : BotTest(SlotFillingRegressionBot) {

    @Test
    fun `should fill slots`() {
        query("TestSlotFilling") hasAnswer "How Much?"
        query("1") hasAnswer "Test OK with slots: {Number=1}"
    }

    @Test
    fun `should interrupt on any intent`() {
        query("TestSlotFilling") hasAnswer "How Much?"
        query("TestSlotFillingInterruption") hasAnswer "Interrupt OK"
    }
}