package com.delightroom.android.gitproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.utility.logI

class UserDetailFragment : Fragment() {

    private val userDetailArgs by navArgs<UserDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logI("userDetailArgs.userId: ${userDetailArgs.userId}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }
}
