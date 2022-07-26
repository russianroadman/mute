package ru.russianroadman.mute.service.tgapi

import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.CommandEnum

interface ReadCommandService {

    fun getCommandsFromMessage(message: Message): List<CommandEnum>

    fun getCommandFromMessage(message: Message): CommandEnum

    fun getCommandWithValue(message: Message): Pair<CommandEnum, String>

}