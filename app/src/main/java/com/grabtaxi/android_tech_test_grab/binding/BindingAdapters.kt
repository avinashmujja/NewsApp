
package com.grabtaxi.android_tech_test_grab.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Data Binding adapters specific to the app.
 */
object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun showImage(view: ImageView, imagUrl: String?) {
        imagUrl?.let {
            Glide.with(view.context)
                .load(imagUrl)
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("publishedtime")
    fun showPublishedDate(view: TextView, time: String?) {
        view.setText(twoDatesBetweenTimeExtended(time))
    }

    private fun twoDatesBetweenTimeExtended(time: String?): String {
        var day : Long = 0
        var hh : Long = 0
        var mm : Long = 0
        var oldDate = Date()
        val calendar: Calendar = Calendar.getInstance()
        try {
            calendar.setTimeInMillis(java.lang.Long.valueOf(time))
            calendar.setTimeZone(TimeZone.getTimeZone("UTC"))
            oldDate = calendar.getTime()
        } catch (e: NumberFormatException) {
            val format = "yyyy-MM-dd'T'HH:mm:ss:ZZZ"
            val dateString = SimpleDateFormat(format, Locale.ENGLISH)
            dateString.setTimeZone(TimeZone.getTimeZone("UTC"))
            try {
                oldDate = dateString.parse(time)
            } catch (e1: ParseException) {
                e1.printStackTrace()
            }
        }
        val cal: Calendar = Calendar.getInstance()
        cal.setTimeZone(TimeZone.getTimeZone("UTC"))
        var cDate = cal.getTime()
        val timeDiff: Long = cDate.getTime() - oldDate.getTime()

        day = TimeUnit.MILLISECONDS.toDays(timeDiff)
        hh = ((TimeUnit.MILLISECONDS.toHours(timeDiff) - TimeUnit.DAYS.toHours(day)))
        mm = ((TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(timeDiff)
        )))

        return if (day / 30 > 0) {
            (day /30).toString() + if (day / 30 > 1) " months ago" else " month ago"
        } else if (day > 0) day.toString() + if (day > 1) " days ago" else " day ago"
        else if (hh > 0) hh.toString() + if (hh > 1) " hours ago"
        else " hour ago"
        else if (mm > 0) mm.toString() + (if (mm > 1) " minutes ago" else " minute ago") else "Just now"
    }

}
