package ru.russianroadman.mute.service.command.impl

import org.springframework.stereotype.Service
import ru.russianroadman.mute.data.CommandEnum
import ru.russianroadman.mute.service.command.CommandSelector
import ru.russianroadman.mute.service.command.CommandService
import ru.russianroadman.mute.util.EnumUtils.name

@Service
class CommandSelectorImpl(
    services: List<CommandService>
) : CommandSelector {

    private val serviceMap: Map<String, CommandService> =
        services.associateBy {
            name(it.representing())
        }

    override fun getDefault(): CommandService {
        return serviceMap.entries.first().value
    }


    override fun getServiceNames(): Set<String> {
        return serviceMap.keys
    }

    override fun getByName(name: String): CommandService {
        return serviceMap[name] ?:
            throw NoSuchElementException(
                "No service associated with $name key"
            )
    }

}