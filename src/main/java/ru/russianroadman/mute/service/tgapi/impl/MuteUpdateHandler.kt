package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update
import ru.russianroadman.mute.service.mute.impl.MuteSelector
import ru.russianroadman.mute.service.tgapi.UpdateHandler

@Service
class MuteUpdateHandler(
    private val muteSelector: MuteSelector
) : UpdateHandler {

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) {
            muteSelector
                .getSelected()
                .examine(update.message)
        }
    }

}