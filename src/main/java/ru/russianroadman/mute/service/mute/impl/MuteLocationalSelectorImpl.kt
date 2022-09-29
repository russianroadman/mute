package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import ru.russianroadman.mute.Location
import ru.russianroadman.mute.service.mute.MuteLocationalSelector
import ru.russianroadman.mute.service.mute.MuteSelector
import ru.russianroadman.mute.service.mute.MuteService

@Service
class MuteLocationalSelectorImpl(
    private val muteSelector: MuteSelector
) : MuteLocationalSelector {

    private val selected = mutableMapOf<Location, MuteService>()

    override fun select(name: String, at: Location) {
        selected[at] = getByName(name)
    }

    override fun getSelected(at: Location): MuteService {
        return getSelectedOrDefault(at)
    }

    override fun getSelectedName(at: Location): String {
        return getSelectedOrDefault(at).getName()
    }

    override fun getByName(name: String): MuteService {
        return muteSelector.getByName(name)
    }

    override fun getServiceNames(): Set<String> {
        return muteSelector.getServiceNames()
    }

    override fun getDefault(): MuteService {
        return muteSelector.getDefault()
    }

    override fun amount(): Int {
        return muteSelector.amount()
    }

    private fun getSelectedOrDefault(at: Location): MuteService {
        return selected[at] ?: return getDefault()
    }

}