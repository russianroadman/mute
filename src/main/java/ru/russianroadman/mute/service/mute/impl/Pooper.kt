package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.service.mute.MuteService
import ru.russianroadman.mute.service.tgapi.MessageSender
import ru.russianroadman.mute.util.Constants.poopEmoji

@Service
class Pooper(
    private val messageSender: MessageSender
) : MuteService {

    override fun examine(message: Message): Boolean {
        if (message.hasVoice()){
            poop(message.messageId, message.chatId.toString())
            return true
        }
        return false
    }

    override fun getName(): String {
        return "Poop"
    }

    private fun poop(messageId: Int, chatId: String){
        messageSender.reply(
            SendMessage(chatId, poopEmoji),
            messageId
        )
    }

}