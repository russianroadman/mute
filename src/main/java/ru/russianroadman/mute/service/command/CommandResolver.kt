package ru.russianroadman.mute.service.command

import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.CommandEnum

interface CommandResolver {

    fun execute(message: Message, value: String?)

    fun representing(): CommandEnum

}