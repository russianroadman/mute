package ru.russianroadman.mute.service.selector

import ru.russianroadman.mute.Location

interface LocationalSelector<T: Any>: Selector<T> {

    /**
     * Selecting implementation by its name
     */
    fun select(name: String, at: Location<*>)

    /**
     * Getting selected implementation
     */
    fun getSelected(at: Location<*>): T

    /**
     * Getting name of selected implementation
     */
    fun getSelectedName(at: Location<*>): String

}