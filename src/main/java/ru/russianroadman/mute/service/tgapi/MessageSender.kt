package ru.russianroadman.mute.service.tgapi

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.CopyMessage
import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.objects.Message
import java.io.Serializable

/**
 * Service for sending message to client
 */
interface MessageSender {

    fun send(message: SendMessage)

    fun send(message: String, chatId: String)

    fun reply(message: SendMessage, replyTo: Int)

    fun reply(message: Message, text: String)

    fun forward(forwardMessage: ForwardMessage)

    fun copy(copyMessage: CopyMessage)

    fun answer(answer: AnswerCallbackQuery)

    fun delete(deleteMessage: DeleteMessage)

    fun delete(message: Message)

    /**
     * Sends text to the chat where the message came from
     */
    fun participate(message: Message, text: String)

    fun <T : Serializable?> execute(target: BotApiMethod<T>): T

}