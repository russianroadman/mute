package ru.russianroadman.mute.service.reader.command_resolvers.resolvers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.russianroadman.mute.data.Command
import ru.russianroadman.mute.service.reader.command_resolvers.CommandResolver
import ru.russianroadman.mute.service.reader.message_resolvers.MessageResolverLocator
import ru.russianroadman.mute.service.reader.message_resolvers.StatefulMessageResolverLocator
import ru.russianroadman.mute.util.ParamUtils

@Service
class SetMessageResolver(
    private val statefulMessageResolverLocator: StatefulMessageResolverLocator
) : CommandResolver {

    private val log = LoggerFactory.getLogger(javaClass)
    private val requiredParamKey = "name"

    override fun resolve(command: Command): Boolean {
        log.info("attempting to resolve [${getKey()}] command. " +
                "resolver is [${javaClass.simpleName}]")
        val name = ParamUtils
            .getString(command.params, requiredParamKey)
            ?: return false.also {
                log.info("could not extract required param key " +
                        "from command params: [$requiredParamKey]")
            }
        statefulMessageResolverLocator.select(name)
        return true
    }

    override fun getKey(): String {
        return "set"
    }

}