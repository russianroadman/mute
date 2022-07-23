package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update
import ru.russianroadman.mute.service.mute.MuteService
import ru.russianroadman.mute.service.tgapi.UpdateHandler

@Service
class MuteUpdateHandler(
    muteServices: List<MuteService>
) : UpdateHandler {

    private val muteServicesMap = muteServices.associateBy {
        it.javaClass.simpleName
    }

    private var selected = muteServicesMap.entries.first().value

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) selected.examine(update.message)
    }

    fun setMuteService(className: String){
        selected = muteServicesMap[className] ?:
            throw java.util.NoSuchElementException(
                "No mute service associated with $className key"
            )
    }

}