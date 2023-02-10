package ru.russianroadman.mute.service.reader.message_resolvers.resolvers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.service.reader.message_resolvers.MessageResolver
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class DeleteVoiceMessage(
    private val ms: MessageSender
) : MessageResolver {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun resolve(message: Message): Boolean {
        log.info("resolving ${javaClass.simpleName}")
        if (message.hasVoice()) {
            ms.delete(message)
            return true
        }
        return false
    }

    override fun getKey(): String {
        return "delete"
    }

}