package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import ru.russianroadman.mute.config.ConfigCredentialsService
import ru.russianroadman.mute.data.CommandKey
import ru.russianroadman.mute.service.locator.CommandKeyPool
import ru.russianroadman.mute.service.tgapi.ReadCommandService

@Service
class ReadCommandServiceImpl(
    private val credentialsService: ConfigCredentialsService,
    private val commandKeyPool: CommandKeyPool
) : ReadCommandService {

    private val command = "bot_command"

    override fun getCommandsFromMessage(message: Message): List<CommandKey> {

        if (!message.isCommand) throw IllegalStateException("Message has no commands")
        val entities = message.entities.filter { checkIsCommand(it) }
        return entities.map {
            getCommandFromEntity(it)
        }

    }

    override fun getCommandFromMessage(message: Message): CommandKey {
        return getCommandsFromMessage(message).first()
    }

    override fun getCommandWithValue(message: Message): Pair<CommandKey, String> {
        return Pair(getCommandFromMessage(message), getValueFromMessage(message))
    }

    private fun getCommandFromEntity(e: MessageEntity): CommandKey {
        return getCommand(getPureCommandFromEntity(e))
    }

    private fun getPureCommandFromEntity(e: MessageEntity): String {
        if (checkIsCommand(e)) {
            return e
                .text
                .substring(e.offset+1, e.length)
                .replace("@${credentialsService.getBotUsername()}", "")
        }
        throw IllegalStateException("MessageEntity is not a command")
    }

    private fun checkIsCommand(e: MessageEntity): Boolean {
        return e.type == command
    }

    private fun getCommand(string: String): CommandKey {
        val keyFromString = CommandKey(string)
        if (commandKeyPool.contains(keyFromString)){
            return keyFromString
        }
        throw IllegalArgumentException("Command received but not recognized: {$string}")
    }

    private fun getValueFromMessage(message: Message): String {
        val entityLength = message.entities.first().length
        return message.text.substring(entityLength).trim()
    }

}