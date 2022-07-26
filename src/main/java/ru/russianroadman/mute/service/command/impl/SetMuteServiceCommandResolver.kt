package ru.russianroadman.mute.service.command.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.CommandEnum
import ru.russianroadman.mute.service.command.CommandResolver
import ru.russianroadman.mute.service.mute.MuteStatefulSelector
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class SetMuteServiceCommandResolver(
    private val muteSelector: MuteStatefulSelector,
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
        return CommandEnum.SET_SERVICE
    }

    private fun setService(chatId: String, value: String){
        muteSelector.select(value)
        messageSender.send(
            SendMessage(chatId, "Selected service $value")
        )
    }

}