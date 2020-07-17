package com.delightroom.android.gitproject.common

import android.app.Application
import com.delightroom.android.gitproject.datasource.local.db.AppDatabase
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.present.viewmodel.ReposDetailViewModel
import com.delightroom.android.gitproject.present.viewmodel.UserDetailViewModel
import com.delightroom.android.gitproject.present.viewmodel.UsersViewModel
import com.delightroom.android.gitproject.repository.ReposInterface
import com.delightroom.android.gitproject.repository.ReposRepository
import com.delightroom.android.gitproject.repository.UserInterface
import com.delightroom.android.gitproject.repository.UserRepository
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(applicationContext)

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    managerModule,
                    viewModelModule,
                    repositoryModule
                )
            )
        }
    }

    private val managerModule = module {
        single { RetrofitManager(applicationContext) }
        single { AppDatabase.getInstance(applicationContext) }
    }

    private val viewModelModule = module {
        viewModel { UsersViewModel(applicationContext, get(), get()) }
        viewModel { UserDetailViewModel(applicationContext, get(), get(), get()) }
        viewModel { ReposDetailViewModel(applicationContext, get(), get()) }
    }

    private val repositoryModule = module {
        factory<UserInterface> { UserRepository(get()) }
        factory<ReposInterface> { ReposRepository(get()) }
    }
}