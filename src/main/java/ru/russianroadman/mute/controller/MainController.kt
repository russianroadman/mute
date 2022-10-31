package ru.russianroadman.mute.controller

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import ru.russianroadman.mute.service.tgapi.BotSessionService

@Controller
class MainController(
    private val sessionService: BotSessionService
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/start")
    fun startBot(){
        sessionService.startSession()
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/stop")
    fun stopBot(){
        sessionService.stopSession()
    }

}
