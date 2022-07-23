package ru.russianroadman.mute.controller

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import ru.russianroadman.mute.service.mute.MuteService
import ru.russianroadman.mute.service.tgapi.BotSessionService
import ru.russianroadman.mute.service.tgapi.impl.MuteUpdateHandler

@Controller
class MainController(
    private val sessionService: BotSessionService,
    private val updateHandler: MuteUpdateHandler,
    muteServices: List<MuteService>
) {

    private val muteServiceAssociations = muteServices.map {
        it.javaClass.simpleName
    }

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
    @GetMapping("/set-muter")
    fun setMuteService(@RequestParam value: String){
        updateHandler.setMuteService(value)
    }

    @ResponseBody
    @GetMapping("/get-muter-associations")
    fun getMuteServices(): List<String> {
        return muteServiceAssociations
    }

}
