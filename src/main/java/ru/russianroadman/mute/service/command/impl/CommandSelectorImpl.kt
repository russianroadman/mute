package ru.russianroadman.mute.service.command.impl

import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.command.CommandSelector
import ru.russianroadman.mute.service.command.CommandResolver
import ru.russianroadman.mute.util.EnumUtils.name

@Service
class CommandSelectorImpl(
    services: List<CommandResolver>
) : CommandSelector {

    private val serviceMap: Map<String, CommandResolver> =
        services.associateBy {
            name(it.representing())
        }

    override fun getDefault(): CommandResolver {
        return serviceMap.entries.first().value
    }


    override fun getServiceNames(): Set<String> {
        return serviceMap.keys
    }

    override fun getByName(name: String): CommandResolver {
        return serviceMap[name] ?:
            throw NoSuchElementException(
                "No service associated with $name key"
            )
    }

}