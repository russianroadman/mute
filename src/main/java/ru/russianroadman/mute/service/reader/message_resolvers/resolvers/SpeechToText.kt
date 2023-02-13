package ru.russianroadman.mute.service.reader.message_resolvers.resolvers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.GetFile
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.config.ConfigCredentialsService
import ru.russianroadman.mute.service.reader.message_resolvers.MessageResolver
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class SpeechToText(
    private val ms: MessageSender,
    private val config: ConfigCredentialsService
): MessageResolver {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun getKey(): String {
        return "translate"
    }

    override fun resolve(message: Message): Boolean {
        if (message.hasVoice()) {
            val downloadLink = getDownloadLink(message.voice.fileId)
            log.info(downloadLink)
        }
        return false
    }

    private fun getDownloadLink(fileId: String): String {
        val file = ms.execute(GetFile(fileId))
        return file.getFileUrl(config.getBotToken())
    }

}