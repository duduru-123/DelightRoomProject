package com.delightroom.android.gitproject.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.databinding.FragmentUsersBinding
import com.delightroom.android.gitproject.datasource.vo.UserVO
import com.delightroom.android.gitproject.present.adapter.UsersPagingAdapter
import com.delightroom.android.gitproject.present.viewmodel.UsersViewModel
import com.delightroom.android.gitproject.utility.logI
import kotlinx.android.synthetic.main.fragment_users.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UsersFragment : Fragment() {

    private val usersViewModel by sharedViewModel<UsersViewModel>()
    private lateinit var binding: FragmentUsersBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logI("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logI("onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
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

        initViewModelCallbackData()
    }

    override fun onResume() {
        super.onResume()
        logI("onResume")
    }

    override fun onPause() {
        super.onPause()
        logI("onPause")
    }

    override fun onStop() {
        super.onStop()
        logI("onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logI("onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logI("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        logI("onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        logI("onDetach")
    }


    /**
     * init layout
     */
    private fun initLayout() {
        swipeRefreshUsers.setOnRefreshListener { usersViewModel.refreshListOfUserVO() }

        with(recyclerUsers) {
            layoutManager = LinearLayoutManager(context)
            adapter = UsersPagingAdapter(onUsersAdapterListener)
        }
    }


    /**
     * init viewModel data of callback
     */
    private fun initViewModelCallbackData() {
        usersViewModel.listOfUserVO.observe(viewLifecycleOwner, Observer { list ->
            val adapter = recyclerUsers.adapter as UsersPagingAdapter
            adapter.submitList(list)

            swipeRefreshUsers.isRefreshing = false
        })
    }


    /**
     * move to UserDetailFragment
     */
    private fun moveToUserDetailFragment(userId: String) {
        val directions =
            UsersFragmentDirections.actionUserFragmentToUserDetailActivity(
                userId
            )
        findNavController().navigate(directions)
    }


    /**
     * onUsersAdapterListener
     * for dealing with event of recyclerUsers
     */
    private val onUsersAdapterListener = object : UsersPagingAdapter.OnUsersAdapterListener {
        override fun onSelectItem(userVO: UserVO) {
            moveToUserDetailFragment(userVO.id)
        }
    }
}
