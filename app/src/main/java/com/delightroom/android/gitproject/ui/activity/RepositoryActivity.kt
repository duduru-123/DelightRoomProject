package com.delightroom.android.gitproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.navArgs
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.present.viewmodel.ReposDetailViewModel
import com.delightroom.android.gitproject.utility.logI
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryActivity : AppCompatActivity() {

    private val reposDetailViewModel by viewModel<ReposDetailViewModel>()
    private val repositoryActivityArgs by navArgs<RepositoryActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        init()
        logI("login: ${repositoryActivityArgs.userLogin}, name: ${repositoryActivityArgs.reposName}")
    }


    /**
     * init
     */
    private fun init() {
        val userLogin = repositoryActivityArgs.userLogin ?: return
        val reposName = repositoryActivityArgs.reposName ?: return

        reposDetailViewModel.setData(userLogin, reposName)

    }
}
