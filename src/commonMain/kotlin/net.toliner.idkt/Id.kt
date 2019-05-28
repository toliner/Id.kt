@file:Suppress("EXPERIMENTAL_API_USAGE")

package net.toliner.idkt

import com.soywiz.klock.DateTimeTz
import kotlin.random.Random

interface Id

interface RandomId : Id {
    val random: Random
}

interface ComparableId<T : Id> : Id, Comparable<T>

interface TimestampId : ComparableId<TimestampId> {
    val time: DateTimeTz
}

interface RandomTimestampId : RandomId, TimestampId

interface Id64bit : Id {
    val value: ULong
}

interface Id128Bit : Id {
    val firstBits: ULong
    val lastBits: ULong
}
