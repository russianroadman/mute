package ru.russianroadman.mute.service.tgapi

interface BotSessionService {

    fun startSession()

    fun stopSession()

    fun isRunning(): Boolean

    fun isNotRunning(): Boolean

}