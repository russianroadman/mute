package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.mute.MuteSelector
import ru.russianroadman.mute.service.mute.MuteService

@Service
class MuteSelectorImpl (
    services: List<MuteService>
) : MuteSelector {

    private val serviceMap: Map<String, MuteService> =
        services.associateBy {
            it.getName()
        }

    private var selected = getDefault()

    /**
     * @throws NoSuchElementException if className is not present in list of services
     */
    override fun select(name: String) {
        selected = serviceMap[name] ?:
            throw java.util.NoSuchElementException(
                "No service associated with $name key"
            )
    }

    override fun getDefault(): MuteService {
        return serviceMap.entries.first().value
    }

    override fun getSelected(): MuteService {
        return selected
    }

    override fun getSelectedName(): String {
        return selected.getName()
    }

    override fun getServiceNames(): Set<String> {
        return serviceMap.keys
    }

}