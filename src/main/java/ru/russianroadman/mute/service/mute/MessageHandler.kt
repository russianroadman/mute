package ru.russianroadman.mute.service.mute

import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.MuteServiceKey
import ru.russianroadman.mute.service.locator.Locatable

interface MessageHandler: Locatable<MuteServiceKey> {

    fun handle(message: Message)

}