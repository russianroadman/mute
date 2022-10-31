package ru.russianroadman.mute.service.locator

import ru.russianroadman.mute.data.CommandKey
import ru.russianroadman.mute.service.command.CommandResolver

interface CommandServiceLocator :
    ServiceLocator<CommandKey, CommandResolver>