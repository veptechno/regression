package com.justai.jaicf.regression

enum class TestEnv(val jaicp: String, val ca: String) {
    TEST_HA("http://test-ha01.lo.test-ai.net", "http://test-ha01.lo.test-ai.net/chatadapter"),
    PROD("https://app.jaicp.com", "https://bot.jaicp.com");
}