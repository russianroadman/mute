package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.mute.MuteSelector
import ru.russianroadman.mute.service.mute.MuteService

@Service
class MuteSelectorImpl (
    private val services: List<MuteService>
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
        selected = getByName(name)
    }

    override fun getDefault(): MuteService {
        return serviceMap.entries.first {
            it.key == "Delete"
        }.value
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

    override fun getByName(name: String): MuteService {
        return serviceMap
            .mapKeys {
                it.key.uppercase()
            }[name.uppercase()] ?:
                throw NoSuchElementException(
                    "No service associated with $name key"
                )
    }

    override fun amount(): Int {
        return services.size
    }

}