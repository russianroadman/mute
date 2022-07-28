package ru.russianroadman.mute.service.mute.impl

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

    /*
     * 5 min penalty
     */
    private var timeoutDurationMillis = 5 * 60 * 1000L

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
        ban(user.id, chatId)
    }

    override fun unban(user: User, chatId: String) {
        unban(user.userName, chatId)
    }

    override fun ban(userId: Long, chatId: String) {
        restrict(userId, chatId)
    }

    override fun unban(userLogin: String, chatId: String) {
        return
    }

    override fun setTimeoutDuration(millis: Long) {
        timeoutDurationMillis = millis
    }

    private fun restrict(userId: Long, chatId: String){
        val permissions = ChatPermissions()
        permissions.canSendMessages = false
        val command = RestrictChatMember(chatId, userId, permissions)
        command.forTimePeriodDuration(Duration.ofMillis(timeoutDurationMillis))
        bot.execute(command)
        sendBannedMessage(chatId)
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

}