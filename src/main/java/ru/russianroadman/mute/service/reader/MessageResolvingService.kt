package ru.russianroadman.mute.service.reader

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update
import ru.russianroadman.mute.service.reader.message_resolvers.StatefulMessageResolverLocator

/**
 * Обработчик ТГ текстовых сообщений
 */
@Service
class MessageResolvingService(
    private val statefulMessageResolverLocator: StatefulMessageResolverLocator
): UpdateResolver {

    override fun resolve(update: Update): Boolean {
        if (!update.hasMessage()) return false
        return statefulMessageResolverLocator.selected().resolve(update.message)
    }

}