package com.justai.jaicf.regression.managers

import com.justai.jaicf.context.manager.mapdb.JacksonMapDbBotContextManager
import com.justai.jaicf.context.manager.mapdb.MapDbBotContextManager

val deprecatedMapDB = MapDbBotContextManager("deprecated.mapdb")
val jacksonMapDB = JacksonMapDbBotContextManager("jackson.mapdb")