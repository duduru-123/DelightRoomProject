package com.delightroom.android.gitproject.present.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.delightroom.android.gitproject.databinding.ItemCommentBinding
import com.delightroom.android.gitproject.databinding.ItemUserReposBinding
import com.delightroom.android.gitproject.datasource.vo.CommentVO
import com.delightroom.android.gitproject.datasource.vo.ReposVO

class CommentsPagingAdapter(
    private val onCommentsAdapterListener: OnCommentsAdapterListener
) :
    PagedListAdapter<CommentVO, CommentsPagingAdapter.ViewHolder>(DiffItemCallback()) {

    interface OnCommentsAdapterListener {
        fun onSelectItem(commentVO: CommentVO)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onCommentsAdapterListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commentVO = getItem(position) ?: return
        holder.init(commentVO)
    }


    /**
     * viewHolder
     */
    class ViewHolder(
        private val binding: ItemCommentBinding,
        private val onCommentsAdapterListener: OnCommentsAdapterListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun init(commentVO: CommentVO) {
            binding.commentVO = commentVO
            binding.clickListener = onCommentsAdapterListener
        }
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<CommentVO>() {
        override fun areItemsTheSame(oldItem: CommentVO, newItem: CommentVO): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CommentVO, newItem: CommentVO): Boolean =
            oldItem == newItem
    }
}