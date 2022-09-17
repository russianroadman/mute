package ru.russianroadman.mute.service.command

import ru.russianroadman.mute.service.selector.Selector

interface CommandSelector: Selector<CommandResolver>