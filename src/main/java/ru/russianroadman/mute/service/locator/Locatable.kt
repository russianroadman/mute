package ru.russianroadman.mute.service.locator

interface Locatable<K: Any> {

    fun getKey(): K

}