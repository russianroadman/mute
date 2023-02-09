package ru.russianroadman.mute.util

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

object TgUtils {

    fun createMessage(message: Message, text: String): SendMessage {
        return SendMessage(message.chatId.toString(), text)
    }

}