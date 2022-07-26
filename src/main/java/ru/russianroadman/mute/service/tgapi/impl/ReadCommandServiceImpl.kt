package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import ru.russianroadman.mute.config.ConfigCredentialsService
import ru.russianroadman.mute.data.CommandEnum
import ru.russianroadman.mute.service.tgapi.ReadCommandService

@Service
class ReadCommandServiceImpl(
    private val credentialsService: ConfigCredentialsService
) : ReadCommandService {

    private val command = "bot_command"

    override fun getCommandsFromMessage(message: Message): List<CommandEnum> {

        if (!messageHasCommand(message)) throw IllegalStateException("Message has no commands")
        val entities = message.entities.filter { checkIsCommand(it) }
        return entities.map {
            getCommandFromEntity(it)
        }

    }

    override fun getCommandFromMessage(message: Message): CommandEnum {
        return getCommandsFromMessage(message).first()
    }

    override fun getCommandWithValue(message: Message): Pair<CommandEnum, String> {
        return Pair(getCommandFromMessage(message), getValueFromMessage(message))
    }

    private fun getCommandFromEntity(e: MessageEntity): CommandEnum {
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

    private fun getCommand(string: String): CommandEnum {
        if (CommandEnum.values().map{it.get().uppercase()}.contains(string.uppercase())){
            return CommandEnum.values().first { it.get().uppercase() == string.uppercase() }
        }
        throw IllegalArgumentException("Command received but not recognized: {$string}")
    }

    private fun messageHasCommand(m: Message): Boolean {
        return m.isCommand
    }

    private fun getValueFromMessage(message: Message): String {
        val entityLength = message.entities.first().length
        return message.text.substring(entityLength).trim()
    }

}