package ru.russianroadman.mute.service.mute.impl

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.User
import ru.russianroadman.mute.service.mute.MuteService
import ru.russianroadman.mute.service.tgapi.MessageSender

@Service
class TimeoutPenalty(
    private val messageSender: MessageSender
) : MuteService {

    private val log = LoggerFactory.getLogger(javaClass)

    /*
     * Key: User, chatID
     * Value: time in millis when penalty started
     */
    private val eraser = mutableMapOf<Pair<User, String>, Long>()

    /*
     * 5 min penalty
     */
    private val penaltyDurationMillis = 1 * 60 * 1000

    override fun examine(message: Message) {
        restrict(message)
    }

    override fun getName(): String {
        return "Eraser"
    }

    private fun restrict(message: Message){
        if (eraser.keys.map{ it.first.id }.contains(message.from.id)){
            deleteMessage(message)
        } else if (message.hasVoice()) {
            penalty(message)
        }
    }

    private fun penalty(message: Message){
        sendBannedMessage(message)
        putIntoMemory(message.from, message.chatId.toString())
        deleteMessage(message)
    }

    private fun deleteMessage(message: Message){
        messageSender
            .delete(
                DeleteMessage(
                    message.chatId.toString(),
                    message.messageId
                )
            )
    }

    private fun putIntoMemory(user: User, chatId: String){
        eraser[Pair(user, chatId)] = System.currentTimeMillis()
    }

    @Scheduled(fixedRate = 1000) // every second
    private fun unmute(){
        val time = System.currentTimeMillis()
        eraser
            .filter { time - it.value > penaltyDurationMillis } // get amnestied
            .keys // get their userIds
            .forEach {
                eraser.remove(it)
                log.info("Un-muted ${it.first.firstName}, userID ${it.first.id}")
                messageSender.send(
                    SendMessage(it.second, "Un-muted ${it.first.firstName}")
                )
            } // remove amnestied users
    }

    private fun sendBannedMessage(message: Message){
        messageSender.send(
            SendMessage(
                message.chatId.toString(),
                "${message.from.firstName}, " +
                    "be quiet for " +
                    "${penaltyDurationMillis / 1000 / 60} " +
                    "minutes ${getRandomCelebratingEmoji()}"
            )
        )
    }

    private fun getRandomCelebratingEmoji(): String {
        return listOf(
            "😂",
            "🤣",
            "😆",
            "😝",
            "😎",
            "😈"
        ).random()
    }

}