package ru.russianroadman.mute.service.command.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.CommandEnum
import ru.russianroadman.mute.service.command.CommandResolver
import ru.russianroadman.mute.service.mute.BanService

@Service
class UnbanCommandResolver(
    private val banServices: List<BanService>
) : CommandResolver {

    override fun execute(message: Message, value: String?) {
        banServices.forEach {
            if (value != null) {
                it.unban(value, message.chatId.toString())
            }
        }
    }

    override fun representing(): CommandEnum {
        return CommandEnum.UNBAN
    }

}