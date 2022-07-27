package ru.russianroadman.mute.service.command.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.CommandEnum
import ru.russianroadman.mute.service.command.CommandResolver
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class InfoCommandResolver(
    private val messageSender: MessageSender
) : CommandResolver {

    override fun execute(message: Message, value: String?) {
        val text = """
            Mute
            Telegram bot for dealing with voice messages in group chats
        """.trimIndent()
        messageSender.send(
            SendMessage(message.chatId.toString(), text)
        )
    }

    override fun representing(): CommandEnum {
        return CommandEnum.INFO
    }

}