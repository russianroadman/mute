package ru.russianroadman.mute.v2

import org.springframework.stereotype.Service
import ru.russianroadman.mute.Location
import ru.russianroadman.mute.v2.command.Command

@Service
class CommandResolverImpl : CommandResolver {

    override fun read(
        location: Location,
        payload: String,
        resolver: (String) -> Command
    ): Command {
        return resolver(payload)
    }

    override fun <T : Command> read(
        location: Location,
        payload: String,
        resolveTo: Class<T>
    ): T {
        TODO("Not yet implemented")
    }
}