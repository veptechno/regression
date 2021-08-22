package com.justai.jaicf.template.connections

import com.justai.jaicf.channel.aimybox.AimyboxChannel
import com.justai.jaicf.channel.alexa.AlexaChannel
import com.justai.jaicf.channel.facebook.FacebookChannel
import com.justai.jaicf.channel.googleactions.jaicp.ActionsFulfillmentDialogflow
import com.justai.jaicf.channel.googleactions.jaicp.ActionsFulfillmentSDK
import com.justai.jaicf.channel.jaicp.JaicpPollingConnector
import com.justai.jaicf.channel.jaicp.channels.ChatApiChannel
import com.justai.jaicf.channel.jaicp.channels.ChatWidgetChannel
import com.justai.jaicf.channel.jaicp.channels.TelephonyChannel
import com.justai.jaicf.channel.slack.SlackChannel
import com.justai.jaicf.channel.telegram.TelegramChannel
import com.justai.jaicf.channel.viber.ViberChannel
import com.justai.jaicf.channel.yandexalice.AliceChannel
import com.justai.jaicf.template.accessToken
import com.justai.jaicf.template.testBot

fun main() {
    JaicpPollingConnector(
        testBot,
        accessToken,
        channels = listOf(
            ActionsFulfillmentDialogflow(),
            ActionsFulfillmentSDK(),
            AlexaChannel,
            AimyboxChannel,
            AliceChannel.Factory(),
            ChatApiChannel,
            ChatWidgetChannel,
            FacebookChannel,
            SlackChannel,
            TelegramChannel,
            TelephonyChannel,
            ViberChannel.Factory()
        )
    ).runBlocking()
}