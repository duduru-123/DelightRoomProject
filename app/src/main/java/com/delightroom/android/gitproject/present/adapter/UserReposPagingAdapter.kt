package com.delightroom.android.gitproject.present.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.delightroom.android.gitproject.databinding.ItemStarredBinding
import com.delightroom.android.gitproject.databinding.ItemUserReposBinding
import com.delightroom.android.gitproject.datasource.vo.ReposVO

class UserReposPagingAdapter() :
    PagedListAdapter<ReposVO, UserReposPagingAdapter.ViewHolder>(DiffItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reposVO = getItem(position) ?: return
        holder.init(reposVO)
    }


    /**
     * viewHolder
     */
    class ViewHolder(
        private val binding: ItemUserReposBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun init(reposVO: ReposVO) {
            binding.reposVO = reposVO
        }
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<ReposVO>() {
        override fun areItemsTheSame(oldItem: ReposVO, newItem: ReposVO): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ReposVO, newItem: ReposVO): Boolean =
            oldItem == newItem
    }
}