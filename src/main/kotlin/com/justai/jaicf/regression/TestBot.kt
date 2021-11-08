package com.justai.jaicf.regression

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.channel.jaicp.logging.JaicpConversationLogger
import com.justai.jaicf.logging.Slf4jConversationLogger
import com.justai.jaicf.regression.scenario.telephony.telephonyScenario

val accessToken = "c0c0b8b0-77f2-4cba-bb2f-01b0fce7b7d9"
//    System.getenv("JAICP_API_TOKEN")
//        ?: print("Enter your JAICP project API key: ").run { readLine() }!!

private val cailaNLUSettings = CailaNLUSettings(
    accessToken = accessToken
)

val testBot = BotEngine(
    scenario = telephonyScenario,
    conversationLoggers = arrayOf(
        JaicpConversationLogger(accessToken, url = "http://test13.lo.test-ai.net/chatadapter"),
        Slf4jConversationLogger()
    ),
    activators = arrayOf(
        RegexActivator
    )`
)
