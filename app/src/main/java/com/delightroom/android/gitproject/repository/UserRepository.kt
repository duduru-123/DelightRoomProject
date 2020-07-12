package com.delightroom.android.gitproject.repository

import com.delightroom.android.gitproject.datasource.remote.api.UserService
import com.delightroom.android.gitproject.datasource.remote.model.User
import com.delightroom.android.gitproject.datasource.remote.model.UserDetail
import com.delightroom.android.gitproject.datasource.remote.model.UserRepos
import com.delightroom.android.gitproject.manager.RetrofitManager

class UserRepository(
    private val retrofitManager: RetrofitManager
) {


    /**
     * request all users
     */
    suspend fun requestUsers(): List<User> {
        return retrofitManager.createApi(UserService::class.java).getUsers()
    }


    /**
     * request user by userId
     */
    suspend fun requestUser(userId: String): UserDetail {
        return retrofitManager.createApi(UserService::class.java).getUser(userId)
    }


    /**
     * request userRepos by userId
     */
    suspend fun requestUserRepos(
        userId: String,
        page: Int? = null,
        pageSize: Int? = null,
        order: String? = "desc"
    ): List<UserRepos> {
        return retrofitManager.createApi(UserService::class.java)
            .getUserRepos(userId, page, pageSize, order)
    }


    /**
     * request starred userRepos by userId
     */
    suspend fun requestUserStarredRepos(
        userId: String,
        page: Int? = null,
        pageSize: Int? = null,
        order: String? = "desc"
    ): List<UserRepos> {
        return retrofitManager.createApi(UserService::class.java)
            .getUserStarredRepos(userId, page, pageSize, order)
    }
}