package ru.russianroadman.mute.data

enum class CommandEnum(private val type: String) {

    GET_SERVICES("services"),
    GET_CURRENT("current"),
    SET_SERVICE("set"),
    INFO("info");

    fun get(): String {
        return type
    }

}