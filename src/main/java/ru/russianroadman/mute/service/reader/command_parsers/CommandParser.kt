package ru.russianroadman.mute.service.reader.command_parsers

import ru.russianroadman.mute.data.Command
import ru.russianroadman.mute.service.locator.Locatable

interface CommandParser: Locatable<String> {

    fun parse(content: String): Command

}