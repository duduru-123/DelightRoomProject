package com.delightroom.android.gitproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import androidx.navigation.ui.NavigationUI
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.present.viewmodel.ReposDetailViewModel
import kotlinx.android.synthetic.main.activity_repos_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReposDetailActivity : AppCompatActivity() {

    private val reposDetailViewModel by viewModel<ReposDetailViewModel>()
    private val reposDetailActivityArgs by navArgs<ReposDetailActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos_detail)

        init()
        initLayout()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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
        NavigationUI.setupWithNavController(bottomNavMenuReposDetail, findNavController(R.id.navHostReposDetail))

        val userLogin = reposDetailActivityArgs.userLogin ?: return
        val reposName = reposDetailActivityArgs.reposName ?: return

        reposDetailViewModel.setData(userLogin, reposName)
    }


    /**
     * init layout
     */
    private fun initLayout() {
        setSupportActionBar(toolbarReposDetail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = reposDetailViewModel.reposName
    }
}
