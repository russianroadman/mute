package ru.russianroadman.mute.service.reader

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SettingsService {

    private val log = LoggerFactory.getLogger(javaClass)

    private val defaultParser = "single_value"

    private val properties = mutableMapOf<String, MutableMap<String, Any?>>()

    fun set(chatId: String, key: String, value: Any?) {
        val entry = properties.getOrPut(chatId) { mutableMapOf() }
        entry[key] = value
        log.info("chat-[$chatId]: property with key [$key] has been set to [$value]")
    }

    fun <T: Any?> get(chatId: String, key: String): T? {
        return (properties[chatId]?.get(key) as? T).also {
            if (it == null) {
                log.info("could not locate property " +
                        "with key [$key] and chatId [$chatId]")
            }
        }
    }

    fun getDefaultParserKey(): String {
        return defaultParser
    }
}