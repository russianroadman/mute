package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.service.mute.MuteService
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class VoiceMessageTerminator(
    private val messageSender: MessageSender
) : MuteService {

    override fun examine(message: Message): Boolean {
        if (message.hasVoice()) {
            delete(message)
            return true
        }
        return false
    }

    override fun getName(): String {
        return "Delete"
    }

    private fun delete(message: Message){
        messageSender
            .delete(
                DeleteMessage(
                    message.chatId.toString(),
                    message.messageId
                )
            )
    }

}