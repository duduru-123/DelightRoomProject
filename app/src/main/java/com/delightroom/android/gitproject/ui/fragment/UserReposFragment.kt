package com.delightroom.android.gitproject.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.common.DefaultItemDecoration
import com.delightroom.android.gitproject.databinding.FragmentUserReposBinding
import com.delightroom.android.gitproject.datasource.vo.ReposVO
import com.delightroom.android.gitproject.present.adapter.RepositoryPagingAdapter
import com.delightroom.android.gitproject.present.viewmodel.UserDetailViewModel
import com.delightroom.android.gitproject.utility.logI
import com.delightroom.android.gitproject.utility.px
import kotlinx.android.synthetic.main.fragment_user_repos.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserReposFragment : Fragment() {

    private val userDetailViewModel by sharedViewModel<UserDetailViewModel>()
    private lateinit var binding: FragmentUserReposBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logI("onCreate")
        logI("StarredFragment.userId: ${userDetailViewModel.userLogin}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logI("onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_repos, container, false)
        binding.viewModel = userDetailViewModel
        binding.lifecycleOwner = this

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logI("onActivityCreated")

        initLayout()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logI("onViewCreated")

        initViewModelCallbackData()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logI("onSaveInstanceState")
    }


    /**
     * init layout
     */
    private fun initLayout() {
        swipeRefreshUserRepos.setOnRefreshListener { userDetailViewModel.refreshListOfUserReposReposVO() }

        with(recyclerUserRepos) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = RepositoryPagingAdapter(onRepositoryAdapterListener)
            addItemDecoration(DefaultItemDecoration(10.px))
        }
    }


    /**
     * init viewModel data of callback
     */
    private fun initViewModelCallbackData() {
        userDetailViewModel.listOfUserReposReposVO.observe(viewLifecycleOwner, Observer { list ->
            val adapter = recyclerUserRepos.adapter as RepositoryPagingAdapter
            adapter.submitList(list)

            swipeRefreshUserRepos.isRefreshing = false
        })
    }


    /**
     * move to RepositoryActivity
     */
    private fun moveToRepositoryActivity(reposVO: ReposVO) {
        val directions = UserReposFragmentDirections.actionUserReposFragmentToRepositoryActivity()
        findNavController().navigate(directions)
    }


    /**
     * onUsersAdapterListener
     * for dealing with event of recyclerUsers
     */
    private val onRepositoryAdapterListener = object :
        RepositoryPagingAdapter.OnRepositoryAdapterListener {
        override fun onSelectItem(reposVO: ReposVO) {
            moveToRepositoryActivity(reposVO)
        }
    }
}
