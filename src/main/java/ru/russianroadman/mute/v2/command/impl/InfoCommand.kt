package ru.russianroadman.mute.v2.command.impl

import org.springframework.stereotype.Component
import ru.russianroadman.mute.v2.command.BlankCommand

@Component
class InfoCommand: BlankCommand {

    override fun description(): String {
        return "Get info about bot"
    }

    override fun name(): String {
        return "info"
    }

    override fun execute() {

    }

}