package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.service.mute.MuteService
import ru.russianroadman.mute.service.tgapi.TelegramMessageSenderService

@Service
class VoiceMessageTerminator(
    private val telegramMessageSenderService: TelegramMessageSenderService
) : MuteService {

    override fun examine(message: Message) {
        if (message.hasVoice()) {
            delete(message)
        }
    }

    private fun delete(message: Message){
        telegramMessageSenderService
            .delete(
                DeleteMessage(
                    message.chatId.toString(),
                    message.messageId
                )
            )
    }

}