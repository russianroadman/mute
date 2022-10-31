package ru.russianroadman.mute.service.locator.impl

import org.springframework.stereotype.Service
import ru.russianroadman.mute.data.MuteServiceKey
import ru.russianroadman.mute.service.locator.MuteStatefulServiceLocator
import ru.russianroadman.mute.service.locator.StatefulServiceLocator
import ru.russianroadman.mute.service.mute.MessageHandler

@Service
class MuteStatefulServiceLocator(
    private val locator: StatefulServiceLocator<
            MuteServiceKey, MessageHandler>
): MuteStatefulServiceLocator {

    override fun selected(): MessageHandler {
        return locator.selected()
    }

    override fun locate(key: MuteServiceKey): MessageHandler {
        return locator.locate(key)
    }

    override fun getKeys(): Set<MuteServiceKey> {
        return locator.getKeys()
    }

    override fun size(): Int {
        return locator.size()
    }


}