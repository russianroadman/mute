package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.MuteServiceKey
import ru.russianroadman.mute.service.mute.RespondClownToVoiceMessageService
import ru.russianroadman.mute.service.locator.MuteServiceKeyPool
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class RespondClownToVoiceMessageServiceImpl(
    private val messageSender: MessageSender,
    private val keyPool: MuteServiceKeyPool
) : RespondClownToVoiceMessageService {

    override fun handle(message: Message) {
        if (message.hasVoice()) {
            messageSender.reply(
                SendMessage(message.chatId.toString(), "🤡"),
                message.messageId
            )
        }
    }

    override fun getKey(): MuteServiceKey {
        return keyPool.findOrPut("clown")
    }
}