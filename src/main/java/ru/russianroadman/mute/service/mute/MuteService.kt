package ru.russianroadman.mute.service.mute

import org.telegram.telegrambots.meta.api.objects.Message

interface MuteService {

    fun examine(message: Message)

    /**
     * Usually for human-readable names
     */
    fun getName(): String
}