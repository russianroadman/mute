package ru.russianroadman.mute.service.reader

import org.slf4j.LoggerFactory
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

    private val log = LoggerFactory.getLogger(javaClass)

    override fun resolve(update: Update): Boolean {
        log.info("attempting to resolve message")
        if (!update.hasMessage()) return false.also {
            log.info("could not resolve message. " +
                    "update doesn't contain message")
        }
        return statefulMessageResolverLocator.selected().resolve(update.message)
    }

}