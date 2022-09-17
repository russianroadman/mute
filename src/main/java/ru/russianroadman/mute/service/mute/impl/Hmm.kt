package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.service.mute.MuteService
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class Hmm(
    private val messageSender: MessageSender
) : MuteService {

    override fun examine(message: Message): Boolean {
        if (message.hasText() && message.text.contains("🤔")){
            respond(message.chatId.toString(), message.messageId)
            return true
        }
        return false
    }

    override fun getName(): String {
        return "hmm"
    }

    private fun respond(chatId: String, replyTo: Int){
        messageSender.reply(
            SendMessage(
                chatId,
                "🤔"
            ),
            replyTo
        )
    }
}