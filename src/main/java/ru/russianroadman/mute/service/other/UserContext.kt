package ru.russianroadman.mute.service.other

import org.telegram.telegrambots.meta.api.objects.User

interface UserContext {

    fun put(chatId: String, user: User)

    fun put(chatId: String, users: Iterable<User>)

    fun get(username: String, chatId: String): User

    fun get(userId: Long, chatId: String): User

    /**
     * @return user, and set of chatIds where this user is
     */
    fun get(username: String): Pair<User, Set<String>>

    /**
     * @return user, and set of chatIds where this user is
     */
    fun get(userId: Long): Pair<User, Set<String>>

}