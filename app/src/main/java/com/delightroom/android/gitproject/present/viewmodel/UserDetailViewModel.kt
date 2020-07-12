package com.delightroom.android.gitproject.present.viewmodel

import androidx.lifecycle.ViewModel
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.repository.UserRepository

class UserDetailViewModel(
    private val retrofitManager: RetrofitManager,
    private val userRepository: UserRepository
) : ViewModel() {

    var userId: String? = null
}