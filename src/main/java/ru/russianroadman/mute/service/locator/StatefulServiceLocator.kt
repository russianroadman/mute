package ru.russianroadman.mute.service.locator

interface StatefulServiceLocator<T: Locatable<K>, K>: Locator<T, K>  {

    fun select(key: K): T?

    fun selected(): T

}