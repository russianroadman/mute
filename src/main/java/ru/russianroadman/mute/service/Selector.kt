package ru.russianroadman.mute.service

interface Selector<T : Any> {

    /**
     * Selecting implementation by its name
     */
    fun select(name: String)

    /**
     * Getting selected implementation
     */
    fun getSelected(): T

    fun getDefault(): T

    fun getSelectedName(): String

    fun getServiceNames(): Set<String>

}