package ru.russianroadman.mute.service.mute

import org.telegram.telegrambots.meta.api.objects.User

interface BanService: MuteService {

    fun ban(user: User, chatId: String)

    fun ban(userLogin: String, chatId: String)

    fun unban(user: User, chatId: String)

    fun unban(userLogin: String, chatId: String)

    fun setTimeoutDuration(millis: Long)

}