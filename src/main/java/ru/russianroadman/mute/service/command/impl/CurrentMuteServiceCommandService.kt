package ru.russianroadman.mute.service.command.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.CommandEnum
import ru.russianroadman.mute.service.command.CommandService
import ru.russianroadman.mute.service.mute.MuteStatefulSelector
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class CurrentMuteServiceCommandService(
    private val muteSelector: MuteStatefulSelector,
    private val messageSender: MessageSender
) : CommandService {

    override fun execute(message: Message, value: String?) {
        getCurrentService(message.chatId.toString())
    }

    override fun representing(): CommandEnum {
        return CommandEnum.GET_CURRENT
    }

    private fun getCurrentService(chatId: String){
        val currentServiceName = muteSelector.getSelectedName()
        messageSender.send(
            SendMessage(chatId, "Current service is: $currentServiceName")
        )
    }

}