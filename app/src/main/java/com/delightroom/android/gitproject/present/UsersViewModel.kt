package com.delightroom.android.gitproject.present

import androidx.lifecycle.ViewModel
import com.delightroom.android.gitproject.repository.UserRepository

class UsersViewModel(
    private val userRepository: UserRepository
): ViewModel() {
}