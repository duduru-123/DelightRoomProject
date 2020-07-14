package com.delightroom.android.gitproject.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.databinding.FragmentReposOverviewBinding
import com.delightroom.android.gitproject.present.viewmodel.ReposDetailViewModel
import com.delightroom.android.gitproject.present.viewmodel.UserDetailViewModel
import com.delightroom.android.gitproject.utility.logI
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ReposOverviewFragment : Fragment() {

    private val reposDetailViewModel by sharedViewModel<ReposDetailViewModel>()
    private lateinit var binding: FragmentReposOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logI("UserOverviewFragment.userId: ${reposDetailViewModel.userLogin}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_repos_overview, container, false)
        binding.viewModel = reposDetailViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logI("onViewCreated")
    }
}
