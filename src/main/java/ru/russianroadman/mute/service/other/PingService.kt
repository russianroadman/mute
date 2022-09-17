package ru.russianroadman.mute.service.other

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class PingService(
    private val servlet: HttpServletRequest
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Scheduled(fixedRate = 5000)
    fun pingSelf(){

//        val httpClient = HttpClient.newBuilder().build()
//        val uri = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
//        val request = HttpRequest.newBuilder().uri(URI(uri)).GET().build()
//        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
//        log.info(response.toString())

    }

}