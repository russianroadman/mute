package ru.russianroadman.mute.service.locator

interface ServiceLocator<K, S> {

    fun locate(key: K): S

    fun getKeys(): Set<K>

    fun size(): Int

}