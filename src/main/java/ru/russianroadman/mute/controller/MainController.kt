package ru.russianroadman.mute.controller

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import ru.russianroadman.mute.service.mute.MuteStatefulSelector
import ru.russianroadman.mute.service.tgapi.BotSessionService

@Controller
class MainController(
    private val sessionService: BotSessionService,
    private val muteSelector: MuteStatefulSelector
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/set-mute-service")
    fun setMuteService(@RequestParam value: String){
        muteSelector.select(value)
    }

    @ResponseBody
    @GetMapping("/get-mute-service-list")
    fun getMuteServices(): Set<String> {
        return muteSelector.getServiceNames()
    }

    @ResponseBody
    @GetMapping("/get-current-service")
    fun getSelectedService(): String {
        return muteSelector.getSelectedName()
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/ping")
    fun ping(){}

}
