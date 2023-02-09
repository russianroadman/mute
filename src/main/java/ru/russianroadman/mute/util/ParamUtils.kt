package ru.russianroadman.mute.util

object ParamUtils {

    fun get(params: Map<String, Any?>, name: String): Any? {
        return params[name]
    }

    fun getString(params: Map<String, Any?>, name: String): String? {
        return params[name]?.toString()
    }

}