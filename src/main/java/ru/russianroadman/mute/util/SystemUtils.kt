package ru.russianroadman.mute.util

import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.yaml.snakeyaml.error.MissingEnvironmentVariableException

object SystemUtils {

    private val env = System.getenv()

    @Suppress("unused")
    fun getParam(key: String): String? {
        return env[key]
    }

    @Suppress("unused")
    fun getUrl(): String {
        return ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .build()
            .toUriString()
    }

    fun getRequiredParam(key: String): String {
        return env[key] ?:
            throw MissingEnvironmentVariableException(
                "Couldn't find system environment variable with key $key"
            )
    }

}