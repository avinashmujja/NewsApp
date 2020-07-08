
package com.grabtaxi.android_tech_test_grab.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.grabtaxi.android_tech_test_grab.util.Utils

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
        view.text = Utils.twoDatesBetweenTimeExtended(time)
    }

}
