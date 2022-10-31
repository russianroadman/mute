package ru.russianroadman.mute.service.locator

import ru.russianroadman.mute.data.CommandKey

interface CommandKeyPool: KeyPool<CommandKey> {

    fun find(key: String): CommandKey

    fun findOrPut(key: String): CommandKey

}