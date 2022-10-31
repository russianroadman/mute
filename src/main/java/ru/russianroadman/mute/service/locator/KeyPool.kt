package ru.russianroadman.mute.service.locator

import ru.russianroadman.mute.exception.KeyIsAlreadyPresentException

interface KeyPool<K: Any>: MutableSet<K> {

    fun insert(key: K): Boolean

    /**
     * @throws KeyIsAlreadyPresentException
     */
    fun tryInsert(key: K)

}