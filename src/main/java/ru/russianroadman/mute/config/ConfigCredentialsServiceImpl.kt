package ru.russianroadman.mute.config

import org.springframework.stereotype.Service
import ru.russianroadman.mute.util.SystemUtils

@Service
class ConfigCredentialsServiceImpl: ConfigCredentialsService {

    override fun getBotToken(): String {
        return SystemUtils.getRequiredParam("MUTE_BOT_TOKEN")
    }

    override fun getBotUsername(): String {
        return SystemUtils.getRequiredParam("MUTE_BOT_NAME")
    }

}