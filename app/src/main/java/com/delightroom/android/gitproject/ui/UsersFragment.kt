package com.delightroom.android.gitproject.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.present.UsersViewModel
import com.delightroom.android.gitproject.utility.logI
import kotlinx.android.synthetic.main.fragment_users.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment() {

    private val usersViewModel by viewModel<UsersViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logI("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logI("onCreateView")
        return inflater.inflate(R.layout.fragment_users, container, false)
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
        textTest.setOnClickListener {
            val direction = UsersFragmentDirections.actionUserFragmentToUserDetailFragment2("test")
            findNavController().navigate(direction)
        }
    }
}
