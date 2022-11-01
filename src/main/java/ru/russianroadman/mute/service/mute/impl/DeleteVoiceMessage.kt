package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.MuteServiceKey
import ru.russianroadman.mute.service.locator.MuteService
import ru.russianroadman.mute.service.locator.MuteServiceKeyPool
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class DeleteVoiceMessage(
    private val messageSender: MessageSender,
    private val keyPool: MuteServiceKeyPool
) : MuteService {

    override fun handle(message: Message) {
        if (message.hasVoice()) {
            messageSender.delete(
                DeleteMessage(
                    message.chatId.toString(),
                    message.messageId
                )
            )
        }
    }

    override fun getKey(): MuteServiceKey {
        return keyPool.findOrPut("delete")
    }

}