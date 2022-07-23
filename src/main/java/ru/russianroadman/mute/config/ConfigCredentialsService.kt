package ru.russianroadman.mute.config

interface ConfigCredentialsService {

    fun getBotToken(): String

    fun getBotUsername(): String

}