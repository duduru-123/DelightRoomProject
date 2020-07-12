package com.delightroom.android.gitproject.datasource.remote.api

import com.delightroom.android.gitproject.datasource.remote.model.User
import com.delightroom.android.gitproject.datasource.remote.model.UserDetail
import com.delightroom.android.gitproject.datasource.remote.model.UserRepos
import retrofit2.http.GET
import retrofit2.http.Path


interface UserService {


    /**
     * request all users
     */
    @GET("/users")
    suspend fun getUsers(): List<User>


    /**
     * request user by userId
     */
    @GET("/users/{userId}")
    suspend fun getUser(
        @Path("userId")
        userId: String
    ): UserDetail


    /**
     * request userRepos by userId
     */
    @GET("/users/{userId}/repos")
    suspend fun getUserRepos(
        @Path("userId")
        userId: String
    ): List<UserRepos>


    /**
     * request user starred repos by userId
     */
    @GET("/users/{userId}/starred")
    suspend fun getUserStarredRepos(
        @Path("userId")
        userId: String
    ): List<UserRepos>
}