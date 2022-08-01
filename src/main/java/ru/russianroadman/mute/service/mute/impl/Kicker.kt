package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.groupadministration.BanChatMember
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.User
import ru.russianroadman.mute.Bot
import ru.russianroadman.mute.service.mute.BanService
import java.time.Duration

@Service
class Kicker(
    private val bot: Bot
) : BanService {

    private var kickDurationMillis = 5 * 60 * 1000L

    override fun examine(message: Message): Boolean {
        if (message.hasVoice()){
            kick(message.chatId.toString(), message.from.id)
            return true
        }
        return false
    }

    override fun getName(): String {
        return "Kick"
    }

    override fun ban(userLogin: String, chatId: String) {
        return
    }

    override fun ban(user: User, chatId: String) {
        ban(user.userName, chatId)
    }

    override fun unban(user: User, chatId: String) {
        unban(user.userName, chatId)
    }

    override fun unban(userLogin: String, chatId: String) {
        return
    }

    override fun setTimeoutDuration(millis: Long) {
        if (millis > 0) kickDurationMillis = millis
    }

    private fun kick(chatId: String, userId: Long) {
        val banHammer = BanChatMember()
        banHammer.chatId = chatId
        banHammer.userId = userId
        banHammer.forTimePeriodDuration(Duration.ofMillis(kickDurationMillis))
        bot.execute(banHammer)
    }

}