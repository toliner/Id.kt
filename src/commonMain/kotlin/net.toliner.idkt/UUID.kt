@file:Suppress("EXPERIMENTAL_API_USAGE", "EXPERIMENTAL_UNSIGNED_LITERALS")

package net.toliner.idkt

import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz
import com.soywiz.klock.TimeSpan
import com.soywiz.klock.toTimeString

sealed class UUID : Id128Bit {
    class V1(override val firstBits: ULong, override val lastBits: ULong) : UUID(), TimestampId {

        override val time: DateTimeTz
            get() {
                val timeLow = firstBits and 0xffffffff00000000UL shr 32
                val timeMid = firstBits and 0x00000000ffff0000UL shl 16
                val timeHigh = firstBits and 0x000000000000fff0UL shl 44
                val timeMilli = (timeLow or timeMid or timeHigh).toDouble() / 10
                DateTime.fromString(TimeSpan(timeMilli).toTimeString())
                TODO()
            }

        override fun compareTo(other: TimestampId) = time.compareTo(other.time)
    }
}