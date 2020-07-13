package com.delightroom.android.gitproject.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.common.DefaultItemDecoration
import com.delightroom.android.gitproject.databinding.FragmentStarredBinding
import com.delightroom.android.gitproject.datasource.vo.ReposVO
import com.delightroom.android.gitproject.present.adapter.RepositoryPagingAdapter
import com.delightroom.android.gitproject.present.viewmodel.UserDetailViewModel
import com.delightroom.android.gitproject.utility.logI
import com.delightroom.android.gitproject.utility.px
import kotlinx.android.synthetic.main.fragment_starred.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StarredFragment : Fragment() {

    private val userDetailViewModel by sharedViewModel<UserDetailViewModel>()
    private lateinit var binding: FragmentStarredBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logI("onAttach")
    }

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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_starred, container, false)
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
        swipeRefreshStarred.setOnRefreshListener { userDetailViewModel.refreshListOfStarredReposVO() }

        with(recyclerStarred) {
            layoutManager = LinearLayoutManager(context)
            adapter = RepositoryPagingAdapter(onRepositoryAdapterListener)
            addItemDecoration(DefaultItemDecoration(10.px))
        }
    }


    /**
     * init viewModel data of callback
     */
    private fun initViewModelCallbackData() {
        userDetailViewModel.listOfStarredReposVO.observe(viewLifecycleOwner, Observer { list ->
            val adapter = recyclerStarred.adapter as RepositoryPagingAdapter
            adapter.submitList(list)

            swipeRefreshStarred.isRefreshing = false
        })
    }


    /**
     * move to RepositoryActivity
     */
    private fun moveToRepositoryActivity(reposVO: ReposVO) {
        val directions = StarredFragmentDirections.actionStarredFragmentToRepositoryActivity()
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
