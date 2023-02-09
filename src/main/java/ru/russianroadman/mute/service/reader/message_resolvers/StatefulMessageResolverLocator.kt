package ru.russianroadman.mute.service.reader.message_resolvers

import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.locator.StatefulServiceLocator

@Service
class StatefulMessageResolverLocator(
    private val locator: MessageResolverLocator
): StatefulServiceLocator<MessageResolver, String> {

    private var selected = locator.locate(locator.getKeys().first())!!

    override fun select(key: String): MessageResolver? {
        return locate(key)?.also { located -> selected = located }
    }

    override fun selected(): MessageResolver {
        return selected
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