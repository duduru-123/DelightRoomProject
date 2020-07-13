package com.delightroom.android.gitproject.present.adapter

import android.view.View
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.delightroom.android.gitproject.datasource.vo.UserVO
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
}