package com.delightroom.android.gitproject.present.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.delightroom.android.gitproject.databinding.ItemUserReposBinding
import com.delightroom.android.gitproject.datasource.vo.ReposVO

class RepositoryPagingAdapter(
    private val onRepositoryAdapterListener: OnRepositoryAdapterListener
) :
    PagedListAdapter<ReposVO, RepositoryPagingAdapter.ViewHolder>(DiffItemCallback()) {

    interface OnRepositoryAdapterListener {
        fun onSelectItem(reposVO: ReposVO)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemUserReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onRepositoryAdapterListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reposVO = getItem(position) ?: return
        holder.init(reposVO)
    }


    /**
     * viewHolder
     */
    class ViewHolder(
        private val binding: ItemUserReposBinding,
        private val onRepositoryAdapterListener: OnRepositoryAdapterListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun init(reposVO: ReposVO) {
            binding.reposVO = reposVO
            binding.clickListener = onRepositoryAdapterListener
        }
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<ReposVO>() {
        override fun areItemsTheSame(oldItem: ReposVO, newItem: ReposVO): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ReposVO, newItem: ReposVO): Boolean =
            oldItem == newItem
    }
}