package com.delightroom.android.gitproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.navArgs
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.utility.logI

class RepositoryActivity : AppCompatActivity() {


    private val repositoryActivityArgs by navArgs<RepositoryActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        logI("login: ${repositoryActivityArgs.userLogin}, name: ${repositoryActivityArgs.reposName}")
    }
}
