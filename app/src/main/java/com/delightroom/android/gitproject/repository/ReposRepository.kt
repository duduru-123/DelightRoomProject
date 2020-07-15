package com.delightroom.android.gitproject.repository

import com.delightroom.android.gitproject.datasource.remote.api.ReposService
import com.delightroom.android.gitproject.datasource.remote.api.UserService
import com.delightroom.android.gitproject.datasource.remote.model.User
import com.delightroom.android.gitproject.datasource.remote.model.UserRepos
import com.delightroom.android.gitproject.datasource.vo.ReposDetailVO
import com.delightroom.android.gitproject.datasource.vo.UserDetailVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.utility.convertToReposDetailVO
import com.delightroom.android.gitproject.utility.convertToUserDetailVO

class ReposRepository(
    private val retrofitManager: RetrofitManager
) : ReposInterface {


    /**
     * request specific repository of user
     */
    override suspend fun requestUserRepository(
        userLogin: String,
        reposName: String
    ): ReposDetailVO {
        val userRepos = retrofitManager.createApi(ReposService::class.java)
            .getUserRepository(userLogin, reposName)

        return userRepos.convertToReposDetailVO()
    }


    /**
     * request languages of repository
     */
    override suspend fun requestLanguages(
        userLogin: String,
        reposName: String
    ): Map<String, Int> {
        return retrofitManager.createApi(ReposService::class.java)
            .getLanguages(userLogin, reposName)
    }
}