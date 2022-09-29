package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import ru.russianroadman.mute.TelegramLocation
import ru.russianroadman.mute.service.mute.MuteLocationalSelector
import ru.russianroadman.mute.service.mute.MuteSelector
import ru.russianroadman.mute.service.other.UserContext
import ru.russianroadman.mute.service.tgapi.CommandHandler
import ru.russianroadman.mute.service.tgapi.UpdateHandler

@Service
class TelegramUpdateHandler(
    private val muteLocationalSelector: MuteLocationalSelector,
    private val commandHandler: CommandHandler,
    private val userContext: UserContext
) : UpdateHandler {

    override fun onUpdateReceived(update: Update) {
        val message = update.message ?: return
        userContext.put(message.chatId.toString(), message.from)
        if (!handleMessage(message) && message.isCommand) {
            handleCommand(message)
        }
    }

    private fun handleCommand(message: Message){
        commandHandler.handle(message)
    }

    private fun handleMessage(message: Message): Boolean {
        return muteLocationalSelector
            .getSelected(TelegramLocation.fromMessage(message))
            .examine(message)
    }

}