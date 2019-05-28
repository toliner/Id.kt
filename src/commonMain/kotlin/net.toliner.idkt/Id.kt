package net.toliner.idkt

import com.soywiz.klock.DateTimeTz
import kotlin.random.Random

interface Id

interface RandomId : Id {
    val random: Random
}

interface ComparableId : Id, Comparable<ComparableId>

interface TimestampId : ComparableId {
    val time: DateTimeTz
}

interface RandomTimestampId : RandomId, TimestampId

interface Id64bit : Id {
    val value: Long
}

interface Id128Bit : Id {
    val firstBits: Long
    val lastBits: Long
}
