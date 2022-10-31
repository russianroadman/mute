package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.service.locator.impl.CommandServiceLocator
import ru.russianroadman.mute.service.tgapi.CommandHandler
import ru.russianroadman.mute.service.tgapi.ReadCommandService

@Service
class CommandHandlerImpl(
    private val readCommandService: ReadCommandService,
    private val commandServiceLocator: CommandServiceLocator
) : CommandHandler {

    override fun handle(message: Message) {

        val commandWithValue = readCommandService
            .getCommandWithValue(message)

        commandServiceLocator
            .locate(commandWithValue.first)
            .execute(message, commandWithValue.second)

    }

}