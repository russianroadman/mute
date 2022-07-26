package ru.russianroadman.mute.service.tgapi.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.CopyMessage
import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.Bot
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class MessageSenderImpl(
    private val bot: Bot
): MessageSender {

    override fun send(message: SendMessage) {
        bot.execute(message)
    }

    override fun reply(message: SendMessage, replyTo: Message) {
        send(message.apply { this.replyToMessageId = replyTo.messageId })
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