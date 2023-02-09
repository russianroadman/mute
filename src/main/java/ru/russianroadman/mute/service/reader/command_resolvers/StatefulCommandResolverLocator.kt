package ru.russianroadman.mute.service.reader.command_resolvers

import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.locator.StatefulServiceLocator

@Service
class StatefulCommandResolverLocator(
    private val locator: CommandResolverLocator
): StatefulServiceLocator<CommandResolver, String> {

    private var selected = locator.locate(locator.getKeys().first())!!

    override fun select(key: String): CommandResolver? {
        return locate(key)?.also { located -> selected = located }
    }

    override fun selected(): CommandResolver {
        return selected
    }

    override fun locate(key: String): CommandResolver? {
        return locator.locate(key)
    }

    override fun amount(): Int {
        return locator.amount()
    }

    override fun getKeys(): Set<String> {
        return locator.getKeys()
    }

}