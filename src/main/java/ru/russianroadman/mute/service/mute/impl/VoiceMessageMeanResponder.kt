package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.service.mute.MuteService
import ru.russianroadman.mute.service.tgapi.MessageSender
import ru.russianroadman.mute.util.Constants.meanEmojisList

@Service
class VoiceMessageMeanResponder(
    private val messageSender: MessageSender
) : MuteService {

    override fun examine(message: Message): Boolean {
        if (message.hasVoice()){
            respond(message)
            return true
        }
        return false
    }

    override fun getName(): String {
        return "Rage"
    }

    private fun respond(message: Message){
        messageSender.reply(
            SendMessage(
                message.chatId.toString(),
                getRandomMeanResponse()
            ),
            message.messageId
        )
    }

    // list of angry emojis in unicode
    private fun getRandomMeanResponse(): String {
        return meanEmojisList.random()
    }

}