package ru.russianroadman.mute.service.tgapi

import org.telegram.telegrambots.meta.api.objects.Update

interface UpdateHandler {

    fun onUpdateReceived(update: Update)

}