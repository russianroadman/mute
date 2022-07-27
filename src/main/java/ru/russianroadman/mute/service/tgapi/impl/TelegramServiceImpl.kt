package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.User
import ru.russianroadman.mute.service.tgapi.TelegramService

@Service
class TelegramServiceImpl : TelegramService {

    override fun getUserById(id: Long): User {
        throw UnsupportedOperationException()
    }

}