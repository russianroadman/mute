package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.service.mute.MuteService
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class VoiceMessageMeanResponder(
    private val messageSender: MessageSender
) : MuteService {

    override fun examine(message: Message) {
        if (message.hasVoice()){
            respond(message)
        }
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
            message
        )
    }

    // list of angry emojis in unicode
    private fun getRandomMeanResponse(): String {
        return listOf(
            "\uD83E\uDD2E",
            "\uD83E\uDD22",
            "\uD83D\uDC7F",
            "\uD83D\uDE21",
            "\uD83E\uDD2C",
            "\uD83D\uDE20"
        ).random()
    }

}