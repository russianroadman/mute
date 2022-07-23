package ru.russianroadman.mute

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import ru.russianroadman.mute.service.tgapi.UpdateHandler

class Bot(
    private val username: String,
    private val token: String,
    private val updateHandler: UpdateHandler
) : TelegramLongPollingBot() {

    override fun getBotUsername(): String {
        return username
    }

    override fun getBotToken(): String {
        return token
    }

    override fun onUpdateReceived(update: Update) {
        updateHandler.onUpdateReceived(update)
    }

}
