package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update
import ru.russianroadman.mute.service.reader.UpdateReader
import ru.russianroadman.mute.service.tgapi.UpdateHandler

@Service
class TelegramUpdateHandler(
    private val updateReader: UpdateReader
) : UpdateHandler {

    override fun onUpdateReceived(update: Update) {
        updateReader.read(update)
    }

}