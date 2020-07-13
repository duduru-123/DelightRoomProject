package com.delightroom.android.gitproject.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import androidx.navigation.ui.NavigationUI
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.present.viewmodel.UserDetailViewModel
import com.delightroom.android.gitproject.utility.logI
import com.delightroom.android.gitproject.utility.showToast
import kotlinx.android.synthetic.main.activity_user_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailActivity : AppCompatActivity() {

    private val userDetailViewModel by viewModel<UserDetailViewModel>()
    private val userDetailActivityArgs by navArgs<UserDetailActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        init()
        initData(userDetailActivityArgs.userLogin)
        initLayout()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
    private fun initData(userLogin: String?) {
        if(userLogin == null) {
            showToast(getString(R.string.no_data))

            finish()
            return
        }

        userDetailViewModel.setData(userLogin)
    }


    /**
     * init layout
     */
    private fun initLayout() {
        setSupportActionBar(toolbarUserDetail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = userDetailViewModel.userLogin
    }
}
