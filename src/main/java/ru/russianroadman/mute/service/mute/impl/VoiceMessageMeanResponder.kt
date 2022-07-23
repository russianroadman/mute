package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.service.mute.MuteService
import ru.russianroadman.mute.service.tgapi.TelegramMessageSenderService

@Service
class VoiceMessageMeanResponder(
    private val telegramMessageSenderService: TelegramMessageSenderService
) : MuteService {

    override fun examine(message: Message) {
        if (message.hasVoice()){
            respond(message)
        }
    }

    private fun respond(message: Message){
        telegramMessageSenderService.reply(
            SendMessage(
                message.chatId.toString(),
                getRandomMeanResponse()
            ),
            message.messageId
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