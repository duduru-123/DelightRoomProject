package com.delightroom.android.gitproject.datasource.remote.api

import com.delightroom.android.gitproject.datasource.remote.model.Comment
import com.delightroom.android.gitproject.datasource.remote.model.User
import com.delightroom.android.gitproject.datasource.remote.model.UserDetail
import com.delightroom.android.gitproject.datasource.remote.model.UserRepos
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ReposService {

    /**
     * request userRepos by userId
     */
    @GET("/users/{userId}/repos")
    suspend fun getUserRepos(
        @Path("userId")
        userId: String,
        @Query("page")
        page: Int? = null,
        @Query("per_page")
        pageSize: Int? = null,
        @Query("order")
        order: String? = null
    ): List<UserRepos>


    /**
     * request user starred repos by userId
     */
    @GET("/users/{userId}/starred")
    suspend fun getUserStarredRepos(
        @Path("userId")
        userId: String,
        @Query("page")
        page: Int? = null,
        @Query("per_page")
        pageSize: Int? = null,
        @Query("order")
        order: String? = null
    ): List<UserRepos>







    /**
     * request user starred repos by userId
     */
    @GET("/repos")
    suspend fun getRepository(
        @Query("page")
        page: Int? = null,
        @Query("per_page")
        pageSize: Int? = null,
        @Query("order")
        order: String? = null
    ): List<UserRepos>


    /**
     * request specific repository of user
     */
    @GET("/repos/{userLogin}/{reposName}")
    suspend fun getUserRepository(
        @Path("userLogin")
        userLogin: String,
        @Path("reposName")
        reposName: String
    ): UserRepos


    /**
     * request languages of repository
     */
    @GET("/repos/{userLogin}/{reposName}/languages")
    suspend fun getLanguages(
        @Path("userLogin")
        userLogin: String,
        @Path("reposName")
        reposName: String
    ): Map<String, Int>


    /**
     * request comments of repository
     */
    @GET("/repos/{userLogin}/{reposName}/comments")
    suspend fun getComments(
        @Path("userLogin")
        userLogin: String,
        @Path("reposName")
        reposName: String,
        @Query("page")
        page: Int? = null,
        @Query("per_page")
        pageSize: Int? = null,
        @Query("order")
        order: String? = null
    ): List<Comment>
}