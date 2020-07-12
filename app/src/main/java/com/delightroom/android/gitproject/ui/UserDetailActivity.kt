package com.delightroom.android.gitproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import androidx.navigation.ui.NavigationUI
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.utility.logI
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    private val userDetailActivityArgs by navArgs<UserDetailActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        logI("userDetailActivityArgs.userId: ${userDetailActivityArgs.userId}")
        val navController = findNavController(R.id.navHostUserDetail)
        val bundle = Bundle().apply { putString("userId", userDetailActivityArgs.userId) }

        navController.setGraph(navController.graph, bundle)

        NavigationUI.setupWithNavController(bottomNavMenuUserDetail, navController)


    }
}
