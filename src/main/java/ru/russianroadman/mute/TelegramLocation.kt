package ru.russianroadman.mute

import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.location.Location

class TelegramLocation(
    private val chatId: String
): Location {

    companion object Construct {
        fun fromMessage(message: Message): TelegramLocation {
            return TelegramLocation(message.chatId.toString())
        }
    }

    override fun getId(): Any {
        return chatId
    }

    override fun hashCode(): Int {
        return getId().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TelegramLocation

        if (chatId != other.chatId) return false

        return true
    }


}