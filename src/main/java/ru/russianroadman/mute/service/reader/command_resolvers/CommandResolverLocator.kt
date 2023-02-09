package ru.russianroadman.mute.service.reader.command_resolvers

import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.locator.ServiceLocator

// todo add key pool
@Service
class CommandResolverLocator(
    services: List<CommandResolver>
): ServiceLocator<CommandResolver, String> {

    private val services = services.associateBy { it.getKey() }

    override fun locate(key: String): CommandResolver? {
        return services[key]
    }

    override fun amount(): Int {
        return services.size
    }

    override fun getKeys(): Set<String> {
        return services.keys
    }

}