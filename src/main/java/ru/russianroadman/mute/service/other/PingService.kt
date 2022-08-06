package ru.russianroadman.mute.service.other

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.InetAddress
import javax.servlet.http.HttpServletRequest

@Service
class PingService(
    private val servlet: HttpServletRequest
) {

    private val restTemplate = RestTemplate()
    private val log = LoggerFactory.getLogger(javaClass)

    @Scheduled(fixedRate = 1000)
    fun pingSelf(){

        InetAddress.getLocalHost().isReachable(10)

    }

}