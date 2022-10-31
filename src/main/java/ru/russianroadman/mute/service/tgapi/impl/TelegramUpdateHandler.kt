package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import ru.russianroadman.mute.service.locator.MuteStatefulServiceLocator
import ru.russianroadman.mute.service.other.UserContext
import ru.russianroadman.mute.service.tgapi.CommandHandler
import ru.russianroadman.mute.service.tgapi.UpdateHandler

@Service
class TelegramUpdateHandler(
    private val muteServiceLocator: MuteStatefulServiceLocator,
    private val commandHandler: CommandHandler,
    private val userContext: UserContext
) : UpdateHandler {

    override fun onUpdateReceived(update: Update) {
        val message = update.message ?: return
        userContext.put(message.chatId.toString(), message.from)
        if (message.isCommand) {
            handleCommand(message)
        } else {
            handleMessage(message)
        }
    }

    private fun handleCommand(message: Message){
        commandHandler.handle(message)
    }

    private fun handleMessage(message: Message) {
        return muteServiceLocator
            .selected()
            .handle(message)
    }

}