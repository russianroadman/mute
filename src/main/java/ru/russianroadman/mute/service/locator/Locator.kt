package ru.russianroadman.mute.service.locator

interface Locator<T, K> {

    /**
    * Retrieve service by key
    * @param key a key to locate a service, associated with the key
    * @return Service, associated with the key
    */
    fun locate(key: K): T?

    /**
    * Get total amount of actual services contained in this locator
    */
    fun amount(): Int

    /**
    * Get all keys, each representing specific service, contained in this locator
    */
    fun getKeys(): Set<K>

}