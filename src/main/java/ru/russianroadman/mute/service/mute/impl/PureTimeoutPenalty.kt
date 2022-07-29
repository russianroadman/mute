package ru.russianroadman.mute.service.mute.impl

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.groupadministration.RestrictChatMember
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.ChatPermissions
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.User
import ru.russianroadman.mute.Bot
import ru.russianroadman.mute.service.mute.BanService
import ru.russianroadman.mute.service.tgapi.MessageSender
import java.time.Duration

@Service
class PureTimeoutPenalty(
    private val bot: Bot,
    private val messageSender: MessageSender
) : BanService {

    private val log = LoggerFactory.getLogger(javaClass)

    /*
     * 5 min penalty
     */
    private var timeoutDurationMillis = 1 * 60 * 1000L

    /*
     * Key: User, chatID
     * Value: time in millis when penalty started
     */
    private val muted = mutableMapOf<Pair<User, String>, Long>()

    override fun examine(message: Message): Boolean {
        if (message.hasVoice()){
            ban(message.from.id, message.chatId.toString())
            return true
        }
        return false
    }

    override fun getName(): String {
        return "Timeout"
    }

    override fun ban(user: User, chatId: String) {
        restrict(user, chatId)
    }

    override fun unban(user: User, chatId: String) {
        unban(user.userName, chatId)
    }

    override fun ban(userId: Long, chatId: String) {
        return
    }

    override fun unban(userLogin: String, chatId: String) {
        val permissions = ChatPermissions()
        permissions.canSendMessages = true
        val user = muted.keys.first { it.first.userName == userLogin }.first
        val command = RestrictChatMember(chatId, user.id, permissions)
        bot.execute(command)
        log.info("Un-muted ${user.firstName}")
    }

    override fun setTimeoutDuration(millis: Long) {
        timeoutDurationMillis = millis
    }

    private fun restrict(user: User, chatId: String){
        val permissions = ChatPermissions()
        permissions.canSendMessages = false
        val command = RestrictChatMember(chatId, user.id, permissions)
        command.forTimePeriodDuration(Duration.ofMillis(timeoutDurationMillis))
        bot.execute(command)
        sendBannedMessage(chatId)
        muted[Pair(user, chatId)] = System.currentTimeMillis()
    }

    private fun sendBannedMessage(chatId: String){
        messageSender.send(
            SendMessage(
                chatId,
                "Banned for ${timeoutDurationMillis/1000/60} " +
                        "min ${getRandomCelebratingEmoji()}"
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

    @Scheduled(fixedRate = 10 * 1000) // every 10 sec
    private fun unmute(){
        val time = System.currentTimeMillis()
        muted
            .filter { time - it.value > timeoutDurationMillis } // get amnestied
            .keys // get their userIds
            .forEach {
                unban(it.first, it.second)
            } // remove amnestied users
    }

}