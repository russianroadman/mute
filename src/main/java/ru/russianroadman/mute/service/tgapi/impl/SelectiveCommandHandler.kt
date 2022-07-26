package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.service.command.CommandSelector
import ru.russianroadman.mute.service.tgapi.CommandHandler
import ru.russianroadman.mute.service.tgapi.ReadCommandService
import ru.russianroadman.mute.util.EnumUtils.name

@Service
class SelectiveCommandHandler(
    private val commandSelector: CommandSelector,
    private val readCommandService: ReadCommandService
) : CommandHandler {

    override fun handle(message: Message) {

        val commandWithValue = readCommandService
            .getCommandWithValue(message)

        commandSelector
            .getByName(
                name(commandWithValue.first)
            )
            .execute(
                message,
                commandWithValue.second
            )

    }

}