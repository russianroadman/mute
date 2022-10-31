package ru.russianroadman.mute.service.command.impl

import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.data.CommandKey
import ru.russianroadman.mute.data.MuteServiceKey
import ru.russianroadman.mute.service.command.CommandResolver
import ru.russianroadman.mute.service.locator.CommandKeyPool
import ru.russianroadman.mute.service.locator.MuteStatefulServiceLocator

@Service
class SelectServiceCommandResolver(
    private val keyPool: CommandKeyPool,
    @Lazy private val muteStatefulServiceLocator: MuteStatefulServiceLocator
) : CommandResolver {

    override fun execute(message: Message, value: String) {
        muteStatefulServiceLocator.locate(MuteServiceKey(value))
    }

    override fun getKey(): CommandKey {
        return keyPool.findOrPut("select")
    }

}