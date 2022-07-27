package ru.russianroadman.mute.service.tgapi

import org.telegram.telegrambots.meta.api.objects.User

interface TelegramService {

    fun getUserById(id: Long): User

}