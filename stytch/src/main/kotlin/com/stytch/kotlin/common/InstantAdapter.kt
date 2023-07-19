package com.stytch.kotlin.common

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.Instant
import java.time.format.DateTimeFormatter

internal class InstantAdapter {
    @ToJson
    fun toJson(instant: Instant): String = instant.toString()

    @FromJson
    fun fromJson(string: String): Instant {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        return Instant.from(formatter.parse(string))
    }
}
