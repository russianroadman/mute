package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.mute.MuteStatefulSelector
import ru.russianroadman.mute.service.mute.MuteService

@Service
class MuteStatefulSelectorImpl (
    services: List<MuteService>
) : MuteStatefulSelector {

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

    override fun getByName(name: String): MuteService {

        return serviceMap
            .mapKeys {
                it.key.uppercase()
            }[name.uppercase()] ?:
                throw java.util.NoSuchElementException(
                    "No service associated with $name key"
                )
    }

}