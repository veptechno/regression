package com.justai.jaicf.regression.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.aimybox.api.aimybox
import com.justai.jaicf.channel.alexa.alexa
import com.justai.jaicf.channel.facebook.api.facebook
import com.justai.jaicf.channel.googleactions.actions
import com.justai.jaicf.channel.jaicp.dto.chatapi
import com.justai.jaicf.channel.jaicp.dto.chatwidget
import com.justai.jaicf.channel.slack.slack
import com.justai.jaicf.channel.telegram.telegram
import com.justai.jaicf.channel.viber.api.viber
import com.justai.jaicf.channel.yandexalice.api.alice

val mainScenario = Scenario {

    append("actions", actionsScenario)
    append("dialogflow", dialogflowScenario)
    append("telegram", telegramScenario)
    append("alexa", alexaScenario)
    append("chatapi", chatApiScenario)
    append("aimybox", aimyboxScenario)
    append("slack", slackScenario)
    append("facebook", facebookScenario)
    append("alice", aliceScenario)
    append("chatwidget", chatWidgetScenario)
    append("viber", viberScenario)

    state("main") {
        activators {
            catchAll()
            anyIntent()
        }

        action {
            reactions.say("Для начала тестирования напишите test")
            request.actions?.run { reactions.go("/actions") }
            request.telegram?.run { reactions.go("/telegram") }
            request.alexa?.run { reactions.go("/alexa") }
            request.chatapi?.run { reactions.go("/chatapi") }
            request.aimybox?.run { reactions.go("/aimybox") }
            request.slack?.run { reactions.go("/slack") }
            request.facebook?.run { reactions.go("/facebook") }
            request.alice?.run { reactions.go("/alice") }
            request.chatwidget?.run { reactions.go("/chatwidget") }
            request.viber?.run { reactions.go("/viber") }
        }
    }
}