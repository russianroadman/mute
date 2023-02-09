package ru.russianroadman.mute.service.reader

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Decides whether message should be resolved or command
 */
@Service
class UpdateTypeResolver(
    private val commandResolver: CommandResolvingService,
    private val messageResolver: MessageResolvingService
) {

    fun resolve(update: Update): Boolean {
        if (update.hasMessage()) {
            return if (update.message.isCommand) commandResolver.resolve(update)
            else messageResolver.resolve(update)
        }
        return false
    }

}