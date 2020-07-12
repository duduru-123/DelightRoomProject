package com.delightroom.android.gitproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import androidx.navigation.ui.NavigationUI
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.present.viewmodel.UserDetailViewModel
import com.delightroom.android.gitproject.utility.logI
import kotlinx.android.synthetic.main.activity_user_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailActivity : AppCompatActivity() {

    private val userDetailViewModel by viewModel<UserDetailViewModel>()
    private val userDetailActivityArgs by navArgs<UserDetailActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        logI("userDetailActivityArgs.userId: ${userDetailActivityArgs.userId}")

        init()
        initData(userDetailActivityArgs.userId)
    }


    /**
     * init
     */
    private fun init() {
        val navController = findNavController(R.id.navHostUserDetail)

        NavigationUI.setupWithNavController(bottomNavMenuUserDetail, navController)
    }


    /**
     * init data
     */
    private fun initData(userId: String?) {
        userDetailViewModel.userId = userId
    }
}
