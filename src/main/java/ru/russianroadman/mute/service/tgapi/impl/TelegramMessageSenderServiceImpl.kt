package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.CopyMessage
import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import ru.russianroadman.mute.Bot
import ru.russianroadman.mute.service.tgapi.TelegramMessageSenderService

@Service
class TelegramMessageSenderServiceImpl(
    private val bot: Bot
): TelegramMessageSenderService {

    override fun send(message: SendMessage) {
        bot.execute(message)
    }

    override fun reply(message: SendMessage, replyToMessageId: Int) {
        send(message.apply { this.replyToMessageId = replyToMessageId })
    }

    override fun forward(forwardMessage: ForwardMessage) {
        bot.execute(forwardMessage)
    }

    override fun copy(copyMessage: CopyMessage) {
        bot.execute(copyMessage)
    }

    override fun answer(answer: AnswerCallbackQuery) {
        bot.execute(answer)
    }

    override fun delete(deleteMessage: DeleteMessage) {
        bot.execute(deleteMessage)
    }

}