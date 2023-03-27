package com.justai.jaicf.regression

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.channel.jaicp.logging.JaicpConversationLogger
import com.justai.jaicf.logging.Slf4jConversationLogger
import com.justai.jaicf.regression.scenario.dialogflowScenario
import com.justai.jaicf.regression.scenario.mainScenario

val accessToken = System.getenv("JAICP_API_TOKEN")
    ?: print("Enter your JAICP project API key: ").run { readLine() }!!

private val cailaNLUSettings = CailaNLUSettings(
    accessToken = accessToken
)

val testBot = BotEngine(
    scenario = mainScenario,
    conversationLoggers = arrayOf(
        JaicpConversationLogger(accessToken),
        Slf4jConversationLogger()
    ),
    activators = arrayOf(
        RegexActivator
    )
)
