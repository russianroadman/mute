package ru.russianroadman.mute.service.locator

interface StatefulServiceLocator<K, S>: ServiceLocator<K, S> {

    fun selected(): S

}