package com.delightroom.android.gitproject.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.databinding.FragmentUserOverviewBinding
import com.delightroom.android.gitproject.present.viewmodel.UserDetailViewModel
import com.delightroom.android.gitproject.utility.logI
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserOverviewFragment : Fragment() {

    private val userDetailViewModel by sharedViewModel<UserDetailViewModel>()
    private lateinit var binding: FragmentUserOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logI("UserOverviewFragment.userId: ${userDetailViewModel.userLogin}")
        initRemoteData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_overview, container, false)
        binding.viewModel = userDetailViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    /**
     * init layout
     */
    private fun initRemoteData() {
        userDetailViewModel.requestUserDetail()
    }
}
