package ru.russianroadman.mute.service.reader.message_resolvers

import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.locator.ServiceLocator

// todo add key pool
@Service
class MessageResolverLocator(
    services: List<MessageResolver>
): ServiceLocator<MessageResolver, String> {

    private val services = services.associateBy { it.getKey() }

    override fun locate(key: String): MessageResolver? {
        return services[key]
    }

    override fun amount(): Int {
        return services.size
    }

    override fun getKeys(): Set<String> {
        return services.keys
    }

}