package ru.russianroadman.mute.service.reader

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.russianroadman.mute.data.Command
import ru.russianroadman.mute.service.reader.command_parsers.CommandParserLocator

@Service
class CommandParsingService(
    private val locator: CommandParserLocator
) {

    fun parse(key: String, content: String): Command {
        return locator.locate(key)?.parse(content)
            ?: throw RuntimeException("Could not locate [$key] command key")
    }

}