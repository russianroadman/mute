package ru.russianroadman.mute.service.reader

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update
import ru.russianroadman.mute.service.reader.command_resolvers.CommandResolverLocator

/**
 * Service for execution commands sent in telegram update
 */
@Service
class CommandResolvingService(
    private val commandResolverLocator: CommandResolverLocator,
    private val parsingService: CommandParsingService
): UpdateResolver {

    private val defaultParserKey = "single_value"
    private val log = LoggerFactory.getLogger(javaClass)

    override fun resolve(update: Update): Boolean {
        log.info("attempting to resolve command")
        if (!update.hasMessage() || !update.message.hasText()) return false.also {
            log.info("could not resolve command. " +
                    "update doesn't contain message with text")
        }
        val command = parsingService.parse(defaultParserKey, update.message.text)
        return commandResolverLocator
            .locate(command.name)
            ?.resolve(command)
            ?: false.also {
                log.info(
                    "command resolver locating failed " +
                            "with key ${command.name}"
                )
            }
    }

}
