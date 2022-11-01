package ru.russianroadman.mute.service.locator.impl

import org.springframework.stereotype.Service
import ru.russianroadman.mute.data.CommandKey
import ru.russianroadman.mute.exception.KeyIsAlreadyPresentException
import ru.russianroadman.mute.service.locator.CommandKeyPool
import javax.naming.OperationNotSupportedException

@Service
class CommandKeyPoolImpl : CommandKeyPool {

    override var size: Int = 0
    private val keys = mutableSetOf<CommandKey>()

    override fun add(element: CommandKey): Boolean {
        return insert(element)
    }

    override fun addAll(elements: Collection<CommandKey>): Boolean {
        return !elements.all { !add(it) }
    }

    override fun clear() {
        return
    }

    override fun remove(element: CommandKey): Boolean {
        return false
    }

    override fun removeAll(elements: Collection<CommandKey>): Boolean {
        return false
    }

    override fun retainAll(elements: Collection<CommandKey>): Boolean {
        return keys.retainAll(elements.toSet())
    }

    override fun contains(element: CommandKey): Boolean {
        return keys.contains(element)
    }

    override fun containsAll(elements: Collection<CommandKey>): Boolean {
        return keys.containsAll(elements)
    }

    override fun isEmpty(): Boolean {
        return keys.isEmpty()
    }

    override fun iterator(): MutableIterator<CommandKey> {
        return keys.iterator()
    }

    override fun insert(key: CommandKey): Boolean {
        val added = keys.add(key)
        if (added) size++
        return added
    }

    override fun tryInsert(key: CommandKey) {
        if (!keys.add(key)) throw KeyIsAlreadyPresentException()
    }

    override fun find(key: String): CommandKey {
        return keys.first { it.key() == key }
    }

    override fun findOrPut(key: String): CommandKey {
        val cKey = CommandKey(key)
        insert(cKey)
        return cKey
    }

}