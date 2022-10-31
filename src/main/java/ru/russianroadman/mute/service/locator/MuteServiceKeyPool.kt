package ru.russianroadman.mute.service.locator

import ru.russianroadman.mute.data.MuteServiceKey

interface MuteServiceKeyPool: KeyPool<MuteServiceKey> {

    fun find(key: String): MuteServiceKey

    fun findOrPut(key: String): MuteServiceKey

}