package ru.russianroadman.mute.service.reader.command_resolvers.resolvers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.russianroadman.mute.data.Command
import ru.russianroadman.mute.location.TgChat
import ru.russianroadman.mute.service.reader.command_resolvers.CommandResolver
import ru.russianroadman.mute.service.reader.message_resolvers.LocationalMessageResolverLocator
import ru.russianroadman.mute.util.ParamUtils

@Service
class SetMessageResolver(
    private val locator: LocationalMessageResolverLocator
) : CommandResolver {

    private val log = LoggerFactory.getLogger(javaClass)
    private val requiredParamKey = "key"
    private val requiredParamChatId = "chatId"

    override fun resolve(command: Command): Boolean {
        log.info("attempting to resolve [${getKey()}] command. " +
                "resolver is [${javaClass.simpleName}]")
        val messageResolverKey = ParamUtils
            .getString(command.params, requiredParamKey)
            ?: return false.also {
                log.info("could not extract required param key " +
                        "from command params: [$requiredParamKey]")
            }
        val chatId = ParamUtils
            .getString(command.params, requiredParamChatId)
            ?: return false.also {
                log.info("could not extract required param key " +
                        "from command params: [$requiredParamChatId]")
            }
        locator.selectAt(TgChat(chatId),messageResolverKey)
        return true
    }

    override fun getKey(): String {
        return "set"
    }

}