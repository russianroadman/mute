package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.service.mute.MuteService

@Service
class DoNothingMuteService : MuteService {

    override fun examine(message: Message): Boolean {
        return false
    }

    override fun getName(): String {
        return "Nothing"
    }

}