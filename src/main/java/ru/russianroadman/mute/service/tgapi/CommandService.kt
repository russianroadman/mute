package ru.russianroadman.mute.service.tgapi

import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.Command

interface CommandService {

    fun getCommandsFromMessage(message: Message): List<Command>

    fun getCommandFromMessage(message: Message): Command

    fun getCommandWithValue(message: Message): Pair<Command, String>

}