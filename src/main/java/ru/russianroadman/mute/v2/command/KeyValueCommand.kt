package ru.russianroadman.mute.v2.command

interface KeyValueCommand: Command {

    fun execute(key: String, value: Any)

}