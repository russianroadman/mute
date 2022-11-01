package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.MuteServiceKey
import ru.russianroadman.mute.service.locator.MuteServiceKeyPool
import ru.russianroadman.mute.service.mute.MessageHandler
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class YesMaster(
    private val keyPool: MuteServiceKeyPool,
    private val ms: MessageSender
) : MessageHandler {

    override fun handle(message: Message) {
        if (message.from.userName == "russianroadman") {
            ms.reply(
                SendMessage(
                    message.chatId.toString(),
                    "At your service, master"
                ),
                message.messageId
            )
        }
    }

    override fun getKey(): MuteServiceKey {
        return keyPool.findOrPut("master")
    }

}