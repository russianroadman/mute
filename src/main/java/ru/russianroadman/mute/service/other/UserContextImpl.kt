package ru.russianroadman.mute.service.other

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.User

@Service
class UserContextImpl : UserContext {

    /**
     * Map of chats and its users
     * Key - chat ID
     * Value - set of users
     */
    private val userMap = mutableMapOf<String, MutableSet<User>>()

    override fun put(chatId: String, user: User) {
        userMap[chatId]?.add(user)
    }

    override fun put(chatId: String, users: Iterable<User>) {
        users.forEach {
            put(chatId, it)
        }
    }

    override fun get(username: String, chatId: String): User {
        return userMap[chatId]
            ?.first { it.userName == username }
            ?: throw NoSuchElementException(
                "Username $username not present in context for chatId: $chatId"
            )
    }

    override fun get(userId: Long, chatId: String): User {
        return userMap[chatId]
            ?.first { it.id == userId }
            ?: throw NoSuchElementException(
                "User ID $userId not present in context for chatId: $chatId"
            )
    }

    override fun get(username: String): Pair<User, Set<String>> {
        TODO("Not yet implemented")
    }

    override fun get(userId: Long): Pair<User, Set<String>> {
        TODO("Not yet implemented")
    }
}