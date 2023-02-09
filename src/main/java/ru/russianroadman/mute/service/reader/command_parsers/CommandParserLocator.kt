package ru.russianroadman.mute.service.reader.command_parsers

import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.locator.ServiceLocator

// todo add key pool
@Service
class CommandParserLocator(
    parsers: List<CommandParser>
): ServiceLocator<CommandParser, String> {

    private val parsers = parsers.associateBy { it.getKey() }

    override fun locate(key: String): CommandParser? {
        return parsers[key]
    }

    override fun amount(): Int {
        return parsers.size
    }

    override fun getKeys(): Set<String> {
        return parsers.keys
    }

}