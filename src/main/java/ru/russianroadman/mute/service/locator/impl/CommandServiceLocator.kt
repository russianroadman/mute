package ru.russianroadman.mute.service.locator.impl

import org.springframework.stereotype.Service
import ru.russianroadman.mute.data.CommandKey
import ru.russianroadman.mute.service.command.CommandResolver
import ru.russianroadman.mute.service.locator.CommandServiceLocator

@Service
class CommandServiceLocator(
    private val locator: StandardServiceLocator<
            CommandKey, CommandResolver>
) : CommandServiceLocator {

    override fun locate(key: CommandKey): CommandResolver {
        return locator.locate(key)
    }

    override fun getKeys(): Set<CommandKey> {
        return locator.getKeys()
    }

    override fun size(): Int {
        return locator.size()
    }
}