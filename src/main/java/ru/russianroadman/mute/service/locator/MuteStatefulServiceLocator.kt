package ru.russianroadman.mute.service.locator

import ru.russianroadman.mute.data.MuteServiceKey
import ru.russianroadman.mute.service.mute.MessageHandler

interface MuteStatefulServiceLocator :
    StatefulServiceLocator<MuteServiceKey, MessageHandler>