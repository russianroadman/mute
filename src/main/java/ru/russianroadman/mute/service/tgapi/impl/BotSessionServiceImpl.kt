package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import ru.russianroadman.mute.Bot
import ru.russianroadman.mute.service.tgapi.BotSessionService
import java.util.logging.Logger

@Service
class BotSessionServiceImpl(
    bot: Bot
) : BotSessionService {

    private val session = TelegramBotsApi(DefaultBotSession::class.java).registerBot(bot)
    private val log = Logger.getLogger(this.javaClass.name)

    override fun startSession() {
        if (isRunning()){
            log.info("Session is already opened")
        } else {
            session.start()
            log.info("Opened session")
        }
    }

    override fun stopSession() {
        if (isNotRunning()){
            log.info("Session is already closed")
        } else {
            session.stop()
            log.info("Closed session")
        }
    }

    override fun isRunning(): Boolean {
        return session.isRunning
    }

    override fun isNotRunning(): Boolean {
        return !isRunning()
    }

}