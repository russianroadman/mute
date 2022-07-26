package ru.russianroadman.mute.service.mute.impl

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.groupadministration.RestrictChatMember
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.ChatPermissions
import org.telegram.telegrambots.meta.api.objects.Message
import ru.russianroadman.mute.Bot
import ru.russianroadman.mute.service.mute.MuteService
import ru.russianroadman.mute.service.tgapi.MessageSender
import java.time.Duration

@Service
class PureTimeoutPenalty(
    private val bot: Bot,
    private val messageSender: MessageSender
) : MuteService {

    private val timeoutDurationMinutes = 1L

    override fun examine(message: Message) {
        if (message.hasVoice()){
            timeout(message)
        }
    }

    override fun getName(): String {
        return "Timeout"
    }

    private fun timeout(message: Message){
        val chatId = message.chatId.toString()
        val userId = message.from.id
        val permissions = ChatPermissions()
        permissions.canSendMessages = false
        val command = RestrictChatMember(chatId, userId, permissions)
        command.forTimePeriodDuration(Duration.ofMinutes(timeoutDurationMinutes))
        bot.execute(command)
        sendBannedMessage(message)
    }

    private fun sendBannedMessage(message: Message){
        messageSender.reply(
            SendMessage(
                message.chatId.toString(),
                "Banned for $timeoutDurationMinutes " +
                        "minutes ${getRandomCelebratingEmoji()}"
            ),
            message
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