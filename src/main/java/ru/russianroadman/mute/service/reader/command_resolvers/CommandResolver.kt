package ru.russianroadman.mute.service.reader.command_resolvers

import ru.russianroadman.mute.data.Command
import ru.russianroadman.mute.service.locator.Locatable

/**
 * Обработчик команды
 */
interface CommandResolver: Locatable<String> {

    fun resolve(command: Command): Boolean

}