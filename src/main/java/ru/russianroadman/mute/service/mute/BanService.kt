package ru.russianroadman.mute.service.mute

import org.telegram.telegrambots.meta.api.objects.User

interface BanService: MuteService {

    fun ban(user: User, chatId: String)

    fun ban(username: String, chatId: String)

    fun unban(user: User, chatId: String)

    fun unban(username: String, chatId: String)

    fun setTimeoutDuration(millis: Long)

}