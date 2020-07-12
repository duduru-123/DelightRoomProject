package com.delightroom.android.gitproject.present.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.delightroom.android.gitproject.databinding.ItemUsersBinding
import com.delightroom.android.gitproject.datasource.vo.UserVO

class UsersPagingAdapter(
    private val onUsersAdapterListener: OnUsersAdapterListener
) : PagedListAdapter<UserVO, UsersPagingAdapter.ViewHolder>(DiffItemCallback()) {

    interface OnUsersAdapterListener {
        fun onSelectItem(userVO: UserVO)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userVO = getItem(position) ?: return
        holder.init(userVO, onUsersAdapterListener)
    }


    /**
     * viewHolder
     */
    class ViewHolder(
        private val binding: ItemUsersBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun init(userVO: UserVO, onUsersAdapterListener: OnUsersAdapterListener) {
            binding.userVO = userVO
            binding.clickListener = onUsersAdapterListener
        }
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<UserVO>() {
        override fun areItemsTheSame(oldItem: UserVO, newItem: UserVO): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserVO, newItem: UserVO): Boolean =
            oldItem == newItem
    }
}