package ru.russianroadman.mute.service.locator.impl

import org.springframework.stereotype.Service
import ru.russianroadman.mute.data.MuteServiceKey
import ru.russianroadman.mute.exception.KeyIsAlreadyPresentException
import ru.russianroadman.mute.service.locator.MuteServiceKeyPool
import javax.naming.OperationNotSupportedException

@Service
class MuteServiceKeyPoolImpl : MuteServiceKeyPool {

    override var size: Int = 0

    override fun add(element: MuteServiceKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun addAll(elements: Collection<MuteServiceKey>): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun remove(element: MuteServiceKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAll(elements: Collection<MuteServiceKey>): Boolean {
        TODO("Not yet implemented")
    }

    override fun retainAll(elements: Collection<MuteServiceKey>): Boolean {
        TODO("Not yet implemented")
    }

    private val keys = mutableSetOf<MuteServiceKey>()

    override fun contains(element: MuteServiceKey): Boolean {
        return keys.contains(element)
    }

    override fun containsAll(elements: Collection<MuteServiceKey>): Boolean {
        return keys.containsAll(elements)
    }

    override fun isEmpty(): Boolean {
        return keys.isEmpty()
    }

    override fun iterator(): MutableIterator<MuteServiceKey> {
        throw OperationNotSupportedException(
            "Can't provide access"
        )
    }

    override fun insert(key: MuteServiceKey): Boolean {
        return keys.add(key)
    }

    override fun tryInsert(key: MuteServiceKey) {
        if (!keys.add(key)) throw KeyIsAlreadyPresentException()
    }

    override fun find(key: String): MuteServiceKey {
        return keys.first { it.key() == key }
    }

    override fun findOrPut(key: String): MuteServiceKey {
        return MuteServiceKey(key).also { insert(it) }
    }

}