package ru.russianroadman.mute.service.locator

import ru.russianroadman.mute.data.MuteServiceKey
import ru.russianroadman.mute.service.mute.MessageHandler

// todo both inheritances are the same
interface MuteService:
    Locatable<MuteServiceKey>,
    MessageHandler