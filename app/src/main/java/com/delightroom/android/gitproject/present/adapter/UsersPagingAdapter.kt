package com.delightroom.android.gitproject.present.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.datasource.vo.UserVO
import kotlinx.android.synthetic.main.item_users.view.*

class UsersPagingAdapter(
    private val onUsersAdapterListener: OnUsersAdapterListener
) : PagedListAdapter<UserVO, UsersPagingAdapter.ViewHolder>(DiffItemCallback()) {

    interface OnUsersAdapterListener {
        fun onSelectItem(userVO: UserVO)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return ViewHolder(view, onUsersAdapterListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userVO = getItem(position) ?: return
        holder.init(userVO)
    }


    /**
     * viewHolder
     */
    class ViewHolder(
        private val view: View,
        private val onUsersAdapterListener: OnUsersAdapterListener
    ) : RecyclerView.ViewHolder(view) {

        fun init(userVO: UserVO) {
            with(view) {
                textUsersName.text = userVO.id
            }
        }
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<UserVO>() {
        override fun areItemsTheSame(oldItem: UserVO, newItem: UserVO): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserVO, newItem: UserVO): Boolean =
            oldItem == newItem
    }
}