package ru.russianroadman.mute.service.reader.command_parsers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.locator.ServiceLocator

// todo add key pool
@Service
class CommandParserLocator(
    parsers: List<CommandParser>
): ServiceLocator<CommandParser, String> {

    private val parsers = parsers.associateBy { it.getKey() }
    private val log = LoggerFactory.getLogger(javaClass)

    override fun locate(key: String): CommandParser? {
        log.info("attempting to locate command parser with key: [$key]")
        log.info("current possible keys are: ${getKeys().joinToString("; ")}")
        return parsers[key]
    }

    override fun default(): CommandParser {
        TODO("Not yet implemented")
    }

    override fun amount(): Int {
        return parsers.size
    }

    override fun getKeys(): Set<String> {
        return parsers.keys
    }

}