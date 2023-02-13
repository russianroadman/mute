package ru.russianroadman.mute.service.reader.command_parsers

import org.springframework.stereotype.Service
import ru.russianroadman.mute.service.locator.StatefulServiceLocator

@Service
class StatefulCommandParserLocator(
    private val locator: CommandParserLocator
): StatefulServiceLocator<CommandParser, String> {

    private var selected = locator.locate(locator.getKeys().first())!!

    override fun select(key: String): CommandParser? {
        return locate(key)?.also { located -> selected = located }
    }

    override fun selected(): CommandParser {
        return selected
    }

    override fun locate(key: String): CommandParser? {
        return locator.locate(key)
    }

    override fun default(): CommandParser {
        TODO("Not yet implemented")
    }

    override fun amount(): Int {
        return locator.amount()
    }

    override fun getKeys(): Set<String> {
        return locator.getKeys()
    }

}