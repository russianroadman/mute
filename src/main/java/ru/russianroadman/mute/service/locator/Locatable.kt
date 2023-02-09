package ru.russianroadman.mute.service.locator

interface Locatable<K> {

    fun getKey(): K

}