package ru.russianroadman.mute.service.command.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.CommandEnum
import ru.russianroadman.mute.service.command.CommandService
import ru.russianroadman.mute.service.mute.MuteStatefulSelector
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class GetServicesCommandService(
    private val messageSender: MessageSender,
    private val muteSelector: MuteStatefulSelector
) : CommandService {

    override fun execute(message: Message, value: String?) {
        getServices(message.chatId.toString())
    }

    override fun representing(): CommandEnum {
        return CommandEnum.GET_SERVICES
    }

    private fun getServices(chatId: String){
        val serviceList = muteSelector.getServiceNames()
        messageSender.send(
            SendMessage(chatId, "List of services:")
        )
        serviceList.forEach {
            messageSender.send(
                SendMessage(chatId, it)
            )
        }
    }

}