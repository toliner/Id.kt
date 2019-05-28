@file:Suppress("EXPERIMENTAL_API_USAGE", "EXPERIMENTAL_UNSIGNED_LITERALS")

package net.toliner.idkt

import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.getAndUpdate

sealed class UUIDProvider {
    class V1 : UUIDProvider(), IdProvider<UUID.V1> {

        private val sequence = atomic(0)

        companion object {
            val gregorioBegin = DateTime(1582, 10, 15)
        }

        override fun new(): UUID.V1 = new((DateTimeTz.nowLocal().utc - gregorioBegin).nanoseconds.toULong())

        fun new(time: ULong): UUID.V1 {
            val timeLow = time and (0x00000000ffffffffUL) shl 32
            val timeMid = time and (0x0000ffff00000000UL) shr 16
            val timeHigh = time and (0x0fff000000000000UL) shr 44
            val version = 0x0000000000000001UL
            val firstBits = timeLow or timeMid or timeHigh or version
            val clockSequence = sequence.getAndUpdate { if (it >= 16384) 0 else it + 1 }.toULong() shl 24
            val node = macAddress
            val res = 0x0000000000000002UL shl 62
            val lastBits = clockSequence or node or res
            return UUID.V1(firstBits, lastBits)
        }

        fun new(timeTz: DateTimeTz) = new((timeTz.utc - gregorioBegin).nanoseconds.toULong())

        override fun fromString(value: String): UUID.V1? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}
