package ru.russianroadman.mute.service.reader.command_parsers.parsers

import org.springframework.stereotype.Service
import ru.russianroadman.mute.data.Command
import ru.russianroadman.mute.service.reader.command_parsers.CommandParser

@Service
class SingleValueParser: CommandParser {

    private val regex = Regex("^\\/[a-z|A-Z]+ *[a-z|A-Z]+ *\$")
    private val commandRegex = Regex("\\/[a-z|A-Z]+")

    override fun parse(content: String): Command {
        if (content.matches(regex)) {
            val command = commandRegex.find(content)
                ?.value
                ?: throw getException(content)
            return Command(
                command,
                mapOf("name" to content.replace(command, ""))
            )
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