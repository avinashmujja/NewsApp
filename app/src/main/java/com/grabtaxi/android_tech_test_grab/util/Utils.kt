package com.grabtaxi.android_tech_test_grab.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object Utils {
    @JvmStatic
    fun twoDatesBetweenTimeExtended(time: String?): String {
        if (time.isNullOrEmpty())
            return time!!

        var day: Long
        var hh: Long
        var mm: Long
        var oldDate = Date()
        val calendar: Calendar = Calendar.getInstance()
        try {
            calendar.timeInMillis = time.toLong()
            calendar.timeZone = TimeZone.getTimeZone("UTC")
            oldDate = calendar.time
        } catch (e: NumberFormatException) {
            val format = "yyyy-MM-dd'T'HH:mm:ss"
            val dateString = SimpleDateFormat(format, Locale.ENGLISH)
            dateString.timeZone = TimeZone.getTimeZone("UTC")
            try {
                oldDate = dateString.parse(time)
            } catch (e1: ParseException) {
                e1.printStackTrace()
            }
        }
        val cal: Calendar = Calendar.getInstance()
        cal.timeZone = TimeZone.getTimeZone("UTC")
        var cDate = cal.time
        val timeDiff: Long = cDate.time - oldDate.time

        day = TimeUnit.MILLISECONDS.toDays(timeDiff)
        hh = ((TimeUnit.MILLISECONDS.toHours(timeDiff) - TimeUnit.DAYS.toHours(day)))
        mm = ((TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(timeDiff)
        )))

        return if (day / 30 > 0) {
            (day / 30).toString() + if (day / 30 > 1) " months ago" else " month ago"
        } else if (day > 0) day.toString() + if (day > 1) " days ago" else " day ago"
        else if (hh > 0) hh.toString() + if (hh > 1) " hours ago"
        else " hour ago"
        else if (mm > 0) mm.toString() + (if (mm > 1) " minutes ago" else " minute ago") else "Just now"
    }
}