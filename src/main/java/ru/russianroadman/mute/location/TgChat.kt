package ru.russianroadman.mute.location

import org.telegram.telegrambots.meta.api.objects.Message

class TgChat(
    private val chatId: String
) : Location {

    companion object {

        fun fromMessage(message: Message): TgChat {
            return TgChat(message.chatId.toString())
        }

    }

    override fun getId(): Any {
        return getChatId()
    }

    fun getChatId(): String {
        return chatId
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TgChat

        if (chatId != other.chatId) return false

        return true
    }

    override fun hashCode(): Int {
        return chatId.hashCode()
    }


}