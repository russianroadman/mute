package ru.russianroadman.mute.data

enum class Command(private val type: String) {

    GET_SERVICES("services"),
    GET_CURRENT("current"),
    SET_SERVICE("set");

    fun get(): String {
        return type
    }

}