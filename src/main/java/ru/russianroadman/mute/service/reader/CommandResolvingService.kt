package ru.russianroadman.mute.service.reader

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update
import ru.russianroadman.mute.service.reader.command_resolvers.CommandResolverLocator
import ru.russianroadman.mute.service.reader.command_resolvers.StatefulCommandResolverLocator

/**
 * Service for execution commands sent in telegram update
 */
@Service
class CommandResolvingService(
    private val commandResolverLocator: CommandResolverLocator,
    private val parsingService: CommandParsingService
): UpdateResolver {

    private val defaultParser = "single_value"

    override fun resolve(update: Update): Boolean {
        if (!update.hasMessage() || !update.message.hasText()) return false
        val command = parsingService.parse(defaultParser, update.message.text)
        return commandResolverLocator
            .locate(command.name)
            ?.resolve(command)
            ?: false
    }

}
