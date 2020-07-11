package com.delightroom.android.gitproject.common

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    dataSourceModule
                )
            )
        }
    }


    private val viewModelModule = module {
    }

    private val repositoryModule = module {
    }

    private val dataSourceModule = module {
    }
}