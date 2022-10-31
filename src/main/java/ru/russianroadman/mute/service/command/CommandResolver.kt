package ru.russianroadman.mute.service.command

import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.CommandKey
import ru.russianroadman.mute.service.locator.Locatable

interface CommandResolver: Locatable<CommandKey> {

    fun execute(message: Message, value: String)

}