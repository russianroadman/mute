package ru.russianroadman.mute.service.locator

interface LocationalStatefulServiceLocator<T: Locatable<K>, K, L>: Locator<T, K>  {

    fun selectAt(location: L, key: K): T?

    fun selectedAt(location: L): T

}