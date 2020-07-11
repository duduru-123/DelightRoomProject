package com.delightroom.android.gitproject.common

import android.app.Application
import com.delightroom.android.gitproject.manager.RetrofitManager
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
                    managerModule,
                    viewModelModule,
                    repositoryModule,
                    dataSourceModule
                )
            )
        }
    }


    private val managerModule = module {
        single { RetrofitManager(applicationContext) }
    }

    private val viewModelModule = module {
    }

    private val repositoryModule = module {
    }

    private val dataSourceModule = module {
    }
}