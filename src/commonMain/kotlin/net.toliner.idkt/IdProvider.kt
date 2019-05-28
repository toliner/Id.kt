package net.toliner.idkt

interface IdProvider<T : Id> {
    fun new(): T
    fun fromString(value: String): T?
}