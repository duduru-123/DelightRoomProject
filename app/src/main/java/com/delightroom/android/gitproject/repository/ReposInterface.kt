package com.delightroom.android.gitproject.repository

import com.delightroom.android.gitproject.datasource.vo.ReposDetailVO

interface ReposInterface {


    /**
     * request specific repository of user
     */
    suspend fun requestUserRepository(userLogin: String, reposName: String): ReposDetailVO


    /**
     * request languages of repository
     */
    suspend fun requestLanguages(userLogin: String, reposName: String): Map<String, Int>
}