package com.justai.jaicf.template

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.caila.CailaIntentActivator
import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.channel.jaicp.logging.JaicpConversationLogger
import com.justai.jaicf.logging.Slf4jConversationLogger
import com.justai.jaicf.template.scenario.mainScenario
import java.util.*

val accessToken =
    System.getenv("JAICP_API_TOKEN")
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
