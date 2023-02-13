package ru.russianroadman.mute.service.reader.command_resolvers.resolvers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.russianroadman.mute.data.Command
import ru.russianroadman.mute.service.reader.SettingsService
import ru.russianroadman.mute.service.reader.command_resolvers.CommandResolver
import ru.russianroadman.mute.util.ParamUtils

@Service
class SettingResolver(
    private val settingsService: SettingsService
): CommandResolver {

    private val log = LoggerFactory.getLogger(javaClass)
    private val requiredParamChatId = "chatId"

    override fun resolve(command: Command): Boolean {
        log.info("attempting to resolve [${getKey()}] command. " +
                "resolver is [${javaClass.simpleName}]")
        val chatId = ParamUtils
            .getString(command.params, requiredParamChatId)
            ?: return false.also {
                log.info("could not extract required param key " +
                        "from command params: [$requiredParamChatId]")
            }
        command
            .params
            .filter { it.key != "chatId" }
            .forEach {
                settingsService.set(chatId, it.key, it.value)
            }
        return command.params.isNotEmpty()
    }

    override fun getKey(): String {
        return "settings"
    }

}