package ru.russianroadman.mute.util

import org.yaml.snakeyaml.error.MissingEnvironmentVariableException

object SystemUtils {

    private val env = System.getenv()

    fun getParam(key: String): String? {
        return env[key]
    }

    fun getRequiredParam(key: String): String {
        return env[key] ?:
            throw MissingEnvironmentVariableException(
                "Couldn't find system environment variable with key $key"
            )
    }

}