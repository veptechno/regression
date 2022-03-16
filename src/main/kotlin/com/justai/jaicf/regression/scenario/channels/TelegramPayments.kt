package com.justai.jaicf.regression.scenario.channels

import com.github.kotlintelegrambot.entities.payments.LabeledPrice
import com.github.kotlintelegrambot.entities.payments.PaymentInvoiceInfo
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.telegram.TelegramEvent
import com.justai.jaicf.channel.telegram.preCheckout
import com.justai.jaicf.channel.telegram.telegram
import com.justai.jaicf.helpers.logging.logger
import java.math.BigInteger

val TelegramPaymentsScenario = Scenario(telegram) {
    fallback {
        reactions.say("Для тестирования напишите invoice")
    }

    state("CreateInvoice") {
        globalActivators {
            regex("invoice")
        }
        action {
            reactions.say("Creating invoice...")
            reactions.sendInvoice(PaymentInvoiceInfo(
                "title",
                "description",
                "unique payload",
                "284685063:TEST:MzQ0MzY4Njc3M2Vk",
                "unique-start-parameter",
                "USD",
                listOf(LabeledPrice("price", BigInteger.valueOf(20_00)))
            ))
        }
    }

    state("preCheckout") {
        activators {
            event(TelegramEvent.PRE_CHECKOUT)
        }
        action(telegram.preCheckout) {
            reactions.answerPreCheckoutQuery(request.preCheckoutQuery.id, true)
            logger.info("PreCheckout successful")
        }
    }

    state("SuccessfulPayment") {
        activators {
            event(TelegramEvent.SUCCESSFUL_PAYMENT)
        }
        action {
            logger.info("Payment Successful")
            reactions.say("Payment Successful!")
        }
    }
}