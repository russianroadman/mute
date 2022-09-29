package ru.russianroadman.mute.v2

import ru.russianroadman.mute.Location
import ru.russianroadman.mute.v2.command.Command

interface CommandResolver {

    fun read(
        location: Location,
        payload: String,
        resolver: (String) -> Command
    ): Command

    fun <T: Command> read(
        location: Location,
        payload: String,
        resolveTo: Class<T>
    ): T

}