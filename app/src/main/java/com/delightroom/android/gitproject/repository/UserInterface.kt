package com.delightroom.android.gitproject.repository

import com.delightroom.android.gitproject.datasource.vo.UserDetailVO

interface UserInterface {


    /**
     * request user by userId
     */
    suspend fun requestUser(userId: String): UserDetailVO
}