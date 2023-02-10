package ru.russianroadman.mute.service.reader.message_resolvers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.russianroadman.mute.location.TgChat
import ru.russianroadman.mute.service.locator.LocationalStatefulServiceLocator

@Service
class LocationalMessageResolverLocator(
    private val locator: MessageResolverLocator
): LocationalStatefulServiceLocator<MessageResolver, String, TgChat> {

    private val log = LoggerFactory.getLogger(javaClass)

    private val state = mutableMapOf<TgChat, MessageResolver>()

    override fun selectAt(location: TgChat, key: String): MessageResolver? {
        log.info("attempting to select message resolver to " +
                "locatable state with key: [$key]")
        return locate(key)?.also {
                located -> state[location] = located
            log.info("successfully selected resolver with key [$key] " +
                    "at location [${location.getId()}]")
        }
    }

    override fun selectedAt(location: TgChat): MessageResolver {
        return state[location]
            .also {
                if (it != null) log.info(
                    "getting selected locatable message resolver. " +
                    "selected resolver key is: ${it.getKey()}"
                )
            }
            ?: throw RuntimeException(
                "could not locate message resolver at location ${location.getId()}"
            )
    }

    override fun locate(key: String): MessageResolver? {
        return locator.locate(key)
    }

    override fun amount(): Int {
        return locator.amount()
    }

    override fun getKeys(): Set<String> {
        return locator.getKeys()
    }

}