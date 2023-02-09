package ru.russianroadman.mute.service.reader

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update
import ru.russianroadman.mute.service.other.UserContext

/**
 * Обработчик ТГ update-ов
 */
@Service
class UpdateReader(
    private val updateTypeResolver: UpdateTypeResolver,
    private val userContext: UserContext
) {

    //todo replace boolean return type with more specific result info

    fun read(update: Update): Boolean {
        putUserIntoContext(update)
        return updateTypeResolver.resolve(update)
    }

    private fun putUserIntoContext(update: Update) {
        if (update.hasMessage()) {
            userContext.put(
                update.message.chatId.toString(),
                update.message.from
            )
        }
    }

}