package ru.russianroadman.mute.service.tgapi

import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.CommandKey

interface ReadCommandService {

    fun getCommandsFromMessage(message: Message): List<CommandKey>

    fun getCommandFromMessage(message: Message): CommandKey

    fun getCommandWithValue(message: Message): Pair<CommandKey, String>

}