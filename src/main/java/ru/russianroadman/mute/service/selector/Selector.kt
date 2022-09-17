package ru.russianroadman.mute.service.selector

interface Selector<T : Any> {

    /**
     * Retrieve instance from name-instance association
     */
    fun getByName(name: String): T

    /**
     * Get names for all instances
     */
    fun getServiceNames(): Set<String>

    /**
     * Default instance
     */
    fun getDefault(): T

    /**
     * Amount of instances held by this selector
     */
    fun amount(): Int

}