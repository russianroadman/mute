package ru.russianroadman.mute.service.reader.message_resolvers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.locator.ServiceLocator

// todo add key pool
@Service
class MessageResolverLocator(
    services: List<MessageResolver>
): ServiceLocator<MessageResolver, String> {

    private val log = LoggerFactory.getLogger(javaClass)
    private val services = services.associateBy { it.getKey() }

    override fun locate(key: String): MessageResolver? {
        log.info("attempting to locate message resolver with key: [$key]")
        log.info("current possible keys are: ${getKeys().joinToString("; ")}")
        return services[key]
    }

    override fun amount(): Int {
        return services.size
    }

    override fun getKeys(): Set<String> {
        return services.keys
    }

}