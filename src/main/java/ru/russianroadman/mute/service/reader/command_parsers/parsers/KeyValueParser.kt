package ru.russianroadman.mute.service.reader.command_parsers.parsers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.russianroadman.mute.data.Command
import ru.russianroadman.mute.service.reader.command_parsers.CommandParser

@Service
class KeyValueParser: CommandParser {

    private val log = LoggerFactory.getLogger(javaClass)

    private val regex = Regex("^\\/[a-z|A-Z]+ *([a-z|A-Z]+=(([a-z|A-Z])|(\\d+)|(_))+; *)+ *\$")
    private val commandRegex = Regex("\\/[a-z|A-Z]+")
    private val keyValueRegex = Regex("[a-z|A-Z]+=(([a-z|A-Z])|(\\d+)|(_))+")

    override fun getKey(): String {
        return "key_value"
    }

    override fun parse(content: String, metadata: Map<String, Any?>): Command {
        log.info("attempting to parse key-value command with content: [$content]")
        if (content.matches(regex)) {

            val commandName = commandRegex
                .find(content)
                ?.value
                ?.replace("/", "")
                ?.trim()
                ?: throw getException(content)

            val params = keyValueRegex
                .findAll(content)
                .map {
                    val entry = it.value.split("=")
                    Pair(entry[0], entry[1])
                }
                .toMap()
                .toMutableMap()

            params["chatId"] = metadata["chatId"].toString()

            return Command(commandName, params).also {
                log.info("created command with name: [${it.name}] " +
                        "and params: [${it.params}]")
            }

        }
        throw getException(content)
    }

    private fun getException(content: String): RuntimeException {
        return RuntimeException("Failed attempt to parse key-value command: [$content]")
    }

}
