package com.delightroom.android.gitproject.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.common.DefaultItemDecoration
import com.delightroom.android.gitproject.databinding.FragmentRepositoryBinding
import com.delightroom.android.gitproject.databinding.FragmentUsersBinding
import com.delightroom.android.gitproject.datasource.vo.ReposVO
import com.delightroom.android.gitproject.datasource.vo.UserVO
import com.delightroom.android.gitproject.present.adapter.RepositoryPagingAdapter
import com.delightroom.android.gitproject.present.adapter.UsersPagingAdapter
import com.delightroom.android.gitproject.present.viewmodel.UsersViewModel
import com.delightroom.android.gitproject.utility.logI
import com.delightroom.android.gitproject.utility.px
import kotlinx.android.synthetic.main.fragment_repository.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryFragment : Fragment() {

    private val usersViewModel by sharedViewModel<UsersViewModel>()
    private lateinit var binding: FragmentRepositoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logI("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logI("onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository, container, false)
        binding.viewModel = usersViewModel
        binding.lifecycleOwner = this

        return binding.root

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logI("onAttach")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logI("onActivityCreated")

        initLayout()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logI("onViewCreated")
    }


    /**
     * init layout
     */
    private fun initLayout() {
        swipeRefreshRepository.setOnRefreshListener { usersViewModel.refreshListOfReposVO() }

        with(recyclerRepository) {
            layoutManager = LinearLayoutManager(context)
            adapter = RepositoryPagingAdapter(onRepositoryAdapterListener)
            addItemDecoration(DefaultItemDecoration(10.px))
        }
    }


    /**
     * onUsersAdapterListener
     * for dealing with event of recyclerUsers
     */
    private val onRepositoryAdapterListener = object :
        RepositoryPagingAdapter.OnRepositoryAdapterListener {
        override fun onSelectItem(reposVO: ReposVO) {
        }
    }
}
