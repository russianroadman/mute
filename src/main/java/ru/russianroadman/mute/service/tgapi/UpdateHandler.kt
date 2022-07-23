package ru.russianroadman.mute.service.tgapi

import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

interface UpdateHandler {

    fun onUpdateReceived(update: Update, sender: AbsSender)

}