package ru.russianroadman.mute.service.other

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class PingService {

    @Scheduled(fixedRate = 1000)
    fun pingSelf(){

    }

}