package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import ru.russianroadman.mute.service.mute.MuteSelector
import ru.russianroadman.mute.service.tgapi.CommandHandler
import ru.russianroadman.mute.service.tgapi.UpdateHandler

@Service
class TelegramUpdateHandler(
    private val muteSelector: MuteSelector,
    private val commandHandler: CommandHandler
) : UpdateHandler {

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) {
            val message = update.message
            when (message.isCommand) {
                true -> handleCommand(message)
                false -> handleMessage(message)
            }
        }
    }

    private fun handleCommand(message: Message){
        commandHandler.handleMessageWithCommand(message)
    }

    private fun handleMessage(message: Message){
        muteSelector
            .getSelected()
            .examine(message)
    }

}