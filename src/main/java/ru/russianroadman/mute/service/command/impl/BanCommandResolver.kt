package ru.russianroadman.mute.service.command.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.CommandEnum
import ru.russianroadman.mute.service.command.CommandResolver
import ru.russianroadman.mute.service.mute.BanService
import ru.russianroadman.mute.service.mute.MuteSelector

/**
 * Bans user accordingly to a selected BanService
 */
@Service
class BanCommandResolver(
    private val muteSelector: MuteSelector
) : CommandResolver {

    override fun execute(message: Message, value: String?) {
        if (value != null){
            val selected = muteSelector.getSelected()
            if (selected is BanService){
                selected.ban(value, message.chatId.toString())
            }
        }
    }

    override fun representing(): CommandEnum {
        return CommandEnum.BAN
    }
}