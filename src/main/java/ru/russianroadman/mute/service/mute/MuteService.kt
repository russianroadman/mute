package ru.russianroadman.mute.service.mute

import org.telegram.telegrambots.meta.api.objects.Message

interface MuteService {

    /**
     * @return true if message was examined positively
     */
    fun examine(message: Message): Boolean

    /**
     * Usually for human-readable names
     */
    fun getName(): String
}