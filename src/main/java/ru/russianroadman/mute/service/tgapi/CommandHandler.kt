package ru.russianroadman.mute.service.tgapi

import org.telegram.telegrambots.meta.api.objects.Message

interface CommandHandler {

    fun handle(message: Message)

}