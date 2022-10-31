package ru.russianroadman.mute.data

class CommandKey(key: String) {

    private val stringKey: String

    init {
        stringKey = key.uppercase()
    }

    fun key(): String {
        return stringKey
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CommandKey

        if (stringKey != other.stringKey) return false

        return true
    }

    override fun hashCode(): Int {
        return stringKey.hashCode()
    }


}