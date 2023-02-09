package ru.russianroadman.mute.service.locator

/**
 * Service locator pattern
 *
 * Aggregates services of type T
 * Each service has to be locatable with key of type K
 *
 *
 */
interface ServiceLocator<T: Locatable<K>, K>: Locator<T, K> 