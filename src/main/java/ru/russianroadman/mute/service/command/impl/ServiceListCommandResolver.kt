package ru.russianroadman.mute.service.command.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.CommandKey
import ru.russianroadman.mute.service.command.CommandResolver
import ru.russianroadman.mute.service.locator.CommandKeyPool
import ru.russianroadman.mute.service.locator.MuteServiceKeyPool
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class ServiceListCommandResolver(
    private val commandKeyPool: CommandKeyPool,
    private val muteServiceKeyPool: MuteServiceKeyPool,
    private val messageSender: MessageSender
) : CommandResolver {

    override fun execute(message: Message, value: String) {
        messageSender.participate(
            message,
            getServiceNames().joinToString("\n")
        )
    }

    override fun getKey(): CommandKey {
        return commandKeyPool.findOrPut("services")
    }

    private fun getServiceNames(): List<String> {
        var c = 1
        return muteServiceKeyPool.map {
            "$c) ${it.key().capitalize()}"
                .also { c ++ }
        }
    }

}