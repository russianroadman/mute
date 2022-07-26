package ru.russianroadman.mute.service

interface Selector<T : Any> {

    /**
     * Retrieve service from name-service association
     */
    fun getByName(name: String): T

    /**
     * Get names for all services
     */
    fun getServiceNames(): Set<String>

    /**
     *
     */
    fun getDefault(): T

}