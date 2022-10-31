package ru.russianroadman.mute.exception

import javassist.NotFoundException

class ServiceNotFoundException(override val message: String?):
    NotFoundException(message)