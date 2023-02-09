package ru.russianroadman.mute.service.reader

import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Resolves telegram updates
 */
interface UpdateResolver {

    fun resolve(update: Update): Boolean

}