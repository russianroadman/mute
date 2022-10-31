package ru.russianroadman.mute.service.locator.impl

import org.springframework.stereotype.Service
import ru.russianroadman.mute.exception.ServiceNotFoundException
import ru.russianroadman.mute.service.locator.Locatable
import ru.russianroadman.mute.service.locator.ServiceLocator

@Service
class StandardServiceLocator<K: Any, S: Locatable<K>>(
    services: List<S>
) : ServiceLocator<K, S> {

    private val serviceMap = services
        .associateBy { it.getKey() }

    override fun locate(key: K): S {
        return get(key)
    }

    override fun getKeys(): Set<K> {
        return serviceMap.keys
    }

    override fun size(): Int {
        return serviceMap.size
    }

    private fun getOrNull(key: K): S? {
        return serviceMap[key]
    }

    private fun get(key: K): S {
        return getOrNull(key) ?: throw ServiceNotFoundException(
            "Could not locate service with key $key"
        )
    }

}