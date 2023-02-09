package ru.russianroadman.mute.service.reader.message_resolvers

import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.service.locator.Locatable

/**
 * Обработчик команды
 */
interface MessageResolver: Locatable<String> {

    fun resolve(message: Message): Boolean

}