package com.delightroom.android.gitproject.repository

import com.delightroom.android.gitproject.datasource.remote.api.UserService
import com.delightroom.android.gitproject.datasource.remote.model.User
import com.delightroom.android.gitproject.manager.RetrofitManager

class UserRepository(
    private val retrofitManager: RetrofitManager
) {


    /**
     * request users
     */
    suspend fun requestUsers(): List<User> {
        return retrofitManager.createApi(UserService::class.java).getUsers()
    }
}