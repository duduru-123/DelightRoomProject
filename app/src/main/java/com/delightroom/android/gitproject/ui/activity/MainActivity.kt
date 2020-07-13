package com.delightroom.android.gitproject.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.present.viewmodel.UsersViewModel
import com.delightroom.android.gitproject.utility.showToast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val usersViewModel by viewModel<UsersViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        initLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.top_menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuSearch -> {
                showToast("search!")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    /**
     * init
     */
    private fun init() {
        NavigationUI.setupWithNavController(bottomNavMenuMain, findNavController(R.id.navHostMain))
    }


    /**
     * init layout
     */
    private fun initLayout() {
        setSupportActionBar(toolbarMain)
    }
}
