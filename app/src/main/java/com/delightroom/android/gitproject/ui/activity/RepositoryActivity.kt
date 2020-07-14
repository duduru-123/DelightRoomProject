package com.delightroom.android.gitproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.navigation.navArgs
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.databinding.ActivityRepositoryBinding
import com.delightroom.android.gitproject.present.viewmodel.ReposDetailViewModel
import kotlinx.android.synthetic.main.activity_repository.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryActivity : AppCompatActivity() {

    private val reposDetailViewModel by viewModel<ReposDetailViewModel>()
    private val repositoryActivityArgs by navArgs<RepositoryActivityArgs>()
    private lateinit var binding: ActivityRepositoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        val userLogin = repositoryActivityArgs.userLogin ?: return
        val reposName = repositoryActivityArgs.reposName ?: return

        reposDetailViewModel.setData(userLogin, reposName)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_repository)
        binding.viewModel = reposDetailViewModel
        binding.lifecycleOwner = this
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
