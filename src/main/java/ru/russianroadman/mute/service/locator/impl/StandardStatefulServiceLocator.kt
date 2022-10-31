package ru.russianroadman.mute.service.locator.impl

import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.locator.Locatable
import ru.russianroadman.mute.service.locator.StatefulServiceLocator

@Service
class StandardStatefulServiceLocator<K: Any, S: Locatable<K>>(
    private val locator: StandardServiceLocator<K, S>
) : StatefulServiceLocator<K, S> {

    private var selected: S = locator.locate(locator.getKeys().random())

    override fun locate(key: K): S {
        selected = locator.locate(key)
        return selected
    }

    override fun getKeys(): Set<K> {
        return locator.getKeys()
    }

    override fun size(): Int {
        return locator.size()
    }

    override fun selected(): S {
        return selected
    }
}