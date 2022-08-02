package ru.russianroadman.mute.service.mute.impl

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.User
import ru.russianroadman.mute.service.mute.BanService
import ru.russianroadman.mute.service.other.UserContext
import ru.russianroadman.mute.service.tgapi.MessageSender
import ru.russianroadman.mute.util.Constants.celebratingEmojisList

@Service
class TimeoutPenalty(
    private val messageSender: MessageSender,
    private val userContext: UserContext
) : BanService {

    private val log = LoggerFactory.getLogger(javaClass)

    /*
     * Key: User, chatID
     * Value: time in millis when penalty started
     */
    private val eraser = mutableMapOf<Pair<User, String>, Long>()

    /*
     * 5 min penalty
     */
    private var penaltyDurationMillis = 1 * 60 * 1000L

    override fun examine(message: Message): Boolean {
        if (eraser.contains(Pair(message.from, message.chatId.toString()))){
            deleteMessage(message.chatId.toString(), message.messageId)
            return true
        } else if (message.hasVoice()) {
            deleteMessage(message.chatId.toString(), message.messageId)
            penalty(message.from, message.chatId.toString())
            return true
        }
        return false
    }

    override fun getName(): String {
        return "Eraser"
    }

    override fun ban(user: User, chatId: String) {
        ban(user.userName, chatId)
    }

    override fun unban(user: User, chatId: String) {
        unban(user.userName, chatId)
    }

    override fun ban(username: String, chatId: String) {
        val user = userContext.get(username, chatId)
        penalty(user, chatId)
    }

    override fun unban(username: String, chatId: String) {
        removePenalty(username.replace("@", ""), chatId)
    }

    override fun setTimeoutDuration(millis: Long) {
        if (millis > 0) penaltyDurationMillis = millis
    }

    private fun penalty(user: User, chatId: String){
        sendBannedMessage(chatId)
        putIntoMemory(user, chatId)
    }

    private fun removePenalty(userLogin: String, chatId: String){
        val key = eraser.keys.first {
            it.first.userName == userLogin &&
            it.second == chatId
        }
        eraser.remove(key)
    }

    private fun deleteMessage(chatId: String, messageId: Int){
        messageSender
            .delete(
                DeleteMessage(
                    chatId,
                    messageId
                )
            )
    }

    private fun putIntoMemory(user: User, chatId: String){
        eraser[Pair(user, chatId)] = System.currentTimeMillis()
    }

    @Scheduled(fixedRate = 10 * 1000) // every 10 sec
    private fun unmute(){
        val time = System.currentTimeMillis()
        eraser
            .filter { time - it.value > penaltyDurationMillis } // get amnestied
            .keys // get their userIds
            .forEach {
                eraser.remove(it)
                log.info("Un-muted ${it.first}")
            } // remove amnestied users
    }

    private fun sendBannedMessage(chatId: String){
        messageSender.send(
            SendMessage(
                chatId,
                "Be quiet for " +
                "${penaltyDurationMillis / 1000 / 60} " +
                "minutes ${getRandomCelebratingEmoji()}"
            )
        )
    }

    private fun getRandomCelebratingEmoji(): String {
        return celebratingEmojisList.random()
    }

}