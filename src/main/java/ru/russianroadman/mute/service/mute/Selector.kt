package ru.russianroadman.mute.service.mute

interface Selector<T : Any> {

    fun select(className: String)

    fun getDefault(): T

    fun getSelected(): T

    fun getServiceNames(): Set<String>

}