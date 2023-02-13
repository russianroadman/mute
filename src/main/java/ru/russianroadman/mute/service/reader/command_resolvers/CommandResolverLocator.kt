package ru.russianroadman.mute.service.reader.command_resolvers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.locator.ServiceLocator

// todo add key pool
@Service
class CommandResolverLocator(
    services: List<CommandResolver>
): ServiceLocator<CommandResolver, String> {

    private val services = services.associateBy { it.getKey() }
    private val log = LoggerFactory.getLogger(javaClass)

    override fun locate(key: String): CommandResolver? {
        log.info("attempting to locate command resolver with key: [$key]")
        log.info("current possible keys are: ${getKeys().joinToString("; ")}")
        return services[key]
    }

    override fun default(): CommandResolver {
        TODO("Not yet implemented")
    }

    override fun amount(): Int {
        return services.size
    }

    override fun getKeys(): Set<String> {
        return services.keys
    }

}