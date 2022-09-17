package ru.russianroadman.mute.service.selector

interface StatefulSelector<T : Any> : Selector<T> {

    /**
     * Selecting implementation by its name into state
     */
    fun select(name: String)

    /**
     * Getting selected implementation from state
     */
    fun getSelected(): T

    /**
     * Getting name of selected implementation from state
     */
    fun getSelectedName(): String

}