package ru.russianroadman.mute.service.command.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.TelegramLocation
import ru.russianroadman.mute.data.CommandEnum
import ru.russianroadman.mute.service.command.CommandResolver
import ru.russianroadman.mute.service.mute.MuteLocationalSelector
import ru.russianroadman.mute.service.mute.impl.MuteLocationalSelectorImpl
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class SelectMuteSericeCommandResolver(
    private val muteLocationalSelector: MuteLocationalSelector,
    private val messageSender: MessageSender
) : CommandResolver {

    override fun execute(message: Message, value: String?) {
        setService(
            message.chatId.toString(),
            value ?:
            throw java.lang.IllegalArgumentException(
                "Value cannot be null for " +
                        "\"${representing().get()}\" " +
                        "command type"
            )
        )
    }

    override fun representing(): CommandEnum {
        return CommandEnum.SELECT
    }

    private fun setService(chatId: String, value: String){
        val location = TelegramLocation(chatId)
        muteLocationalSelector.select(value, location)
        messageSender.send(
            SendMessage(chatId,
                "Selected service ${
                    muteLocationalSelector
                        .getSelected(location)
                        .getName()
                }"
            )
        )
    }

}