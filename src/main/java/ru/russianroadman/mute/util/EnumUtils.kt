package ru.russianroadman.mute.util

import ru.russianroadman.mute.data.CommandEnum

object EnumUtils {

    fun name(command: CommandEnum): String {
        return command.toString()
    }

    fun command(name: String): CommandEnum {
        return CommandEnum.valueOf(name)
    }

}