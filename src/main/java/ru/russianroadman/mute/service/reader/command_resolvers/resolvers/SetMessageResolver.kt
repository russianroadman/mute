package ru.russianroadman.mute.service.reader.command_resolvers.resolvers

import org.springframework.stereotype.Service
import ru.russianroadman.mute.data.Command
import ru.russianroadman.mute.service.reader.command_resolvers.CommandResolver
import ru.russianroadman.mute.service.reader.message_resolvers.MessageResolverLocator
import ru.russianroadman.mute.service.reader.message_resolvers.StatefulMessageResolverLocator
import ru.russianroadman.mute.util.ParamUtils

@Service
class SetMessageResolver(
    private val statefulMessageResolverLocator: StatefulMessageResolverLocator
) : CommandResolver {

    override fun resolve(command: Command): Boolean {
        val name = ParamUtils
            .getString(command.params, "name")
            ?: return false
        statefulMessageResolverLocator.select(name)
        return true
    }

    override fun getKey(): String {
        return "set"
    }

}