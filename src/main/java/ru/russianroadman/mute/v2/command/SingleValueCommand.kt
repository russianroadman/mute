package ru.russianroadman.mute.v2.command

interface SingleValueCommand: Command {

    fun execute(value: Any)

}