package ru.russianroadman.mute.service.reader.message_resolvers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.locator.StatefulServiceLocator

@Service
class StatefulMessageResolverLocator(
    private val locator: MessageResolverLocator
): StatefulServiceLocator<MessageResolver, String> {

    private val log = LoggerFactory.getLogger(javaClass)
    private var selected = locator.default()

    override fun select(key: String): MessageResolver? {
        log.info("attempting to select message resolver to state with key: [$key]")
        return locate(key)?.also {
            located -> selected = located
            log.info("successfully selected resolver with key ${selected.getKey()}")
        }
    }

    override fun selected(): MessageResolver {
        log.info("getting selected message resolver. " +
                "selected resolver key is: ${selected.getKey()}")
        return selected
    }

    override fun locate(key: String): MessageResolver? {
        return locator.locate(key)
    }

    override fun default(): MessageResolver {
        TODO("Not yet implemented")
    }

    override fun amount(): Int {
        return locator.amount()
    }

    override fun getKeys(): Set<String> {
        return locator.getKeys()
    }

}