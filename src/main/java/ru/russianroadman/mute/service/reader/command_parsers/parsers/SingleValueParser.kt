package ru.russianroadman.mute.service.reader.command_parsers.parsers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.russianroadman.mute.data.Command
import ru.russianroadman.mute.service.reader.command_parsers.CommandParser
import ru.russianroadman.mute.util.ParamUtils

@Service
class SingleValueParser: CommandParser {

    private val log = LoggerFactory.getLogger(javaClass)

    private val regex = Regex("^\\/[a-z|A-Z]+ *[a-z|A-Z]+ *\$")
    private val commandRegex = Regex("\\/[a-z|A-Z]+")

    override fun parse(content: String, metadata: Map<String, Any?>): Command {
        log.info("attempting to parse single-value command with content: [$content]")
        if (content.matches(regex)) {
            val command = commandRegex.find(content)
                ?.value
                ?: throw getException(content)
            return Command(
                command.replace("/", "").trim(),
                mapOf(
                    "key" to content.replace(command, "").trim(),
                    "chatId" to ParamUtils.getString(metadata, "chatId")
                )
            ).also {
                log.info("created command with name: [${it.name}] and params: [${it.params}]")
            }
        }
        throw getException(content)
    }

    override fun getKey(): String {
        return "single_value"
    }

    private fun getException(content: String): RuntimeException {
        return RuntimeException("Failed attempt to parse single-value command: [$content]")
    }

}