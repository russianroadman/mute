package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.CopyMessage
import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.russianroadman.mute.service.tgapi.MessageFactory

@Service
class MessageFactoryImpl : MessageFactory {

    override fun getSendMessage(chatId: String, responseText: String): SendMessage {
        return SendMessage(chatId, responseText)
    }

    override fun getForwardMessage(
        forwardChatId: String,
        originalChatId: String,
        originalMessageId: Int
    ): ForwardMessage {
        return ForwardMessage(forwardChatId, originalChatId, originalMessageId)
    }

    override fun getCopyMessage(
        copyChatId: String,
        originalChatId: String,
        originalMessageId: Int
    ): CopyMessage {
        return CopyMessage(copyChatId, originalChatId, originalMessageId)
    }

}