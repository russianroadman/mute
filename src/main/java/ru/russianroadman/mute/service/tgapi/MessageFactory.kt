package ru.russianroadman.mute.service.tgapi

import org.telegram.telegrambots.meta.api.methods.CopyMessage
import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

interface MessageFactory {

    fun getSendMessage(chatId: String, responseText: String): SendMessage

    fun getForwardMessage(forwardChatId: String, originalChatId: String, originalMessageId: Int): ForwardMessage

    fun getCopyMessage(copyChatId: String, originalChatId: String, originalMessageId: Int): CopyMessage

}