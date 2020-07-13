package com.delightroom.android.gitproject.present.adapter

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.delightroom.android.gitproject.datasource.vo.UserVO
import com.delightroom.android.gitproject.utility.getDateYYYYMMdd
import com.delightroom.android.gitproject.utility.logI
import com.facebook.drawee.view.SimpleDraweeView

object BindingAdapters {
    /**
     * binding list of UserVO
     */
    @JvmStatic
    @BindingAdapter("app:items")
    fun setBindItem(view: RecyclerView, items: PagedList<UserVO>?) {
        logI("items: ${items?.size}")
        view.adapter?.run {
            if (this is UsersPagingAdapter) {
                this.submitList(items)
            }
        }
    }


    /**
     * binding list of UserVO
     */
    @JvmStatic
    @BindingAdapter("app:goneUnlessByText")
    fun goneUnlessByText(view: View, content: String?) {
        view.visibility = if (content == null || content == "") {
            View.GONE
        } else {
            View.VISIBLE
        }
    }


    /**
     * update imageView by url
     */
    @JvmStatic
    @BindingAdapter("app:updateUrl")
    fun updateUrl(imageView: SimpleDraweeView, url: String?) {
        imageView.setImageURI(url)
    }


    /**
     * update date format YYYY MMM dd
     */
    @JvmStatic
    @BindingAdapter("app:updateDate")
    fun updateDate(textView: TextView, time: Long) {
        textView.text = getDateYYYYMMdd(time)
    }


    /**
     * update file size
     */
    @JvmStatic
    @BindingAdapter("app:updateSize")
    fun updateSize(textView: TextView, size: Long) {
        //todo update size
        textView.text = "${size}MB"
    }


    /**
     * update language color
     */
    @JvmStatic
    @BindingAdapter("app:updateLanguageColor")
    fun updateLanguageColor(textView: TextView, language: String?) {
        if (language == null) return

        val colorId = when (language) {
            "Kotlin" -> Color.MAGENTA
            "JavaScript" -> Color.GRAY
            "HTML" -> Color.RED
            "TypeScript" -> Color.BLUE
            else -> Color.GREEN
        }

        textView.setTextColor(colorId)
    }
}