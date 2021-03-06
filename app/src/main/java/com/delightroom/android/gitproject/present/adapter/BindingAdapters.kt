package com.delightroom.android.gitproject.present.adapter

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.delightroom.android.gitproject.datasource.vo.CommentVO
import com.delightroom.android.gitproject.datasource.vo.ReposVO
import com.delightroom.android.gitproject.datasource.vo.UserVO
import com.delightroom.android.gitproject.utility.getDateYYYYMMdd
import com.delightroom.android.gitproject.utility.logI
import com.facebook.drawee.view.SimpleDraweeView

object BindingAdapters {
    /**
     * binding list of UserVO
     */
    @JvmStatic
    @BindingAdapter("app:listOfUserVO")
    fun setBindItemForUserVO(view: RecyclerView, items: PagedList<UserVO>?) {
        logI("items: ${items?.size}")
        view.adapter?.run {
            if (this is UsersPagingAdapter) {
                this.submitList(items)
            }
        }
    }


    /**
     * binding list of ReposVO
     */
    @JvmStatic
    @BindingAdapter("app:listOfReposVO")
    fun setBindItemForReposVO(view: RecyclerView, items: PagedList<ReposVO>?) {
        logI("items: ${items?.size}")
        view.adapter?.run {
            if (this is RepositoryPagingAdapter) {
                this.submitList(items)
            }
        }
    }


    /**
     * binding list of CommentVO
     */
    @JvmStatic
    @BindingAdapter("app:listOfCommentVO")
    fun setBindItemForCommentVO(view: RecyclerView, items: PagedList<CommentVO>?) {
        logI("items: ${items?.size}")
        view.adapter?.run {
            if (this is CommentsPagingAdapter) {
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
        textView.text = when {
            size / (1024 * 1024) >= 1 -> "${size / (1024 * 1024)}GB"
            size / (1024) >= 1 -> "${size / (1024)}MB"
            else -> "${size}KB"
        }
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
            "JavaScript" -> Color.MAGENTA
            "HTML" -> Color.RED
            "TypeScript" -> Color.BLUE
            else -> Color.GREEN
        }

        textView.setTextColor(colorId)
    }


    /**
     * update isRefreshing of swipeRefreshLayout
     */
    @JvmStatic
    @BindingAdapter("app:updateSwipeRefreshLayout")
    fun updateLanguageColor(layout: SwipeRefreshLayout, isRefreshing: Boolean) {
        if (!isRefreshing) layout.isRefreshing = isRefreshing

    }
}