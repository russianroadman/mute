package ru.russianroadman.mute.service.tgapi.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.CommandEnum
import ru.russianroadman.mute.service.mute.MuteSelector
import ru.russianroadman.mute.service.tgapi.CommandHandler
import ru.russianroadman.mute.service.tgapi.CommandService
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class CommandHandlerImpl(
    private val muteSelector: MuteSelector,
    private val messageSender: MessageSender,
    private val commandService: CommandService
) : CommandHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun handleMessageWithCommand(message: Message) {

        val commandWithValue = commandService.getCommandWithValue(message)
        log.info(commandWithValue.toString())
        val command = commandWithValue.first
        val value = commandWithValue.second
        val chatId = message.chatId.toString()

        when(command){
            CommandEnum.GET_SERVICES -> getServices(chatId)
            CommandEnum.SET_SERVICE -> setService(chatId, value)
            CommandEnum.GET_CURRENT -> getCurrentService(chatId)
        }

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

    private fun setService(chatId: String, value: String){
        muteSelector.select(value)
        messageSender.send(
            SendMessage(chatId, "Selected service $value")
        )
    }

    private fun getCurrentService(chatId: String){
        val currentServiceName = muteSelector.getSelectedName()
        messageSender.send(
            SendMessage(chatId, "Current service is: $currentServiceName")
        )
    }

}