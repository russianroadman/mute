package ru.russianroadman.mute.data

enum class CommandEnum(private val type: String) {

    GET_SERVICES("services"),
    GET_CURRENT("current"),
    SET_SERVICE("set"),
    INFO("info"),
    UNBAN("unban"),
    SET_DURATION("duration"),
    BAN("ban"),
    SELECT("select");

    fun get(): String {
        return type
    }

}