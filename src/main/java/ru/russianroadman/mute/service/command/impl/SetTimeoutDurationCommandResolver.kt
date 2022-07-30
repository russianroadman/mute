package ru.russianroadman.mute.service.command.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.CommandEnum
import ru.russianroadman.mute.service.command.CommandResolver

@Service
class SetTimeoutDurationCommandResolver : CommandResolver {

    override fun execute(message: Message, value: String?) {
        return
    }

    override fun representing(): CommandEnum {
        return CommandEnum.SET_DURATION
    }
}