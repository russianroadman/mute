package ru.russianroadman.mute.service.locator.impl

import org.springframework.stereotype.Service
import ru.russianroadman.mute.data.MuteServiceKey
import ru.russianroadman.mute.service.mute.MessageHandler
import ru.russianroadman.mute.service.locator.MuteServiceLocator

@Service
class MuteServiceLocator(
    private val locator: StandardServiceLocator<
            MuteServiceKey, MessageHandler>
): MuteServiceLocator {

    override fun getKeys(): Set<MuteServiceKey> {
        return locator.getKeys()
    }

    override fun locate(key: MuteServiceKey): MessageHandler {
        return locator.locate(key)
    }

    override fun size(): Int {
        return locator.size()
    }

}