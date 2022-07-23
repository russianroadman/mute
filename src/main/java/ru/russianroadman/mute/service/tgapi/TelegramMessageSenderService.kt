package ru.russianroadman.mute.service.tgapi

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.CopyMessage
import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.bots.AbsSender

/**
 * Service for sending message to client
 */
interface TelegramMessageSenderService {

    fun send(message: SendMessage)

    fun reply(message: SendMessage, replyToMessageId: Int)

    fun forward(forwardMessage: ForwardMessage)

    fun copy(copyMessage: CopyMessage)

    fun answer(answer: AnswerCallbackQuery)

    fun delete(deleteMessage: DeleteMessage)


}