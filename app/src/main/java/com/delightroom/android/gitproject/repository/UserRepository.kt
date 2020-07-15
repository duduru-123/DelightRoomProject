package com.delightroom.android.gitproject.repository

import com.delightroom.android.gitproject.datasource.remote.api.UserService
import com.delightroom.android.gitproject.datasource.vo.UserDetailVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.utility.convertToUserDetailVO

class UserRepository(
    private val retrofitManager: RetrofitManager
) : UserInterface {


    /**
     * request user by userId
     * @param userId
     *
     * @return UserDetailVO
     */
    override suspend fun requestUser(userId: String): UserDetailVO {
        val userDetail = retrofitManager.createApi(UserService::class.java).getUser(userId)
        return userDetail.convertToUserDetailVO()
    }

}