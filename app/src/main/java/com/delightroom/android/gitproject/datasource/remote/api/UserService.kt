package com.delightroom.android.gitproject.datasource.remote.api

import com.delightroom.android.gitproject.datasource.remote.model.User
import retrofit2.http.GET


interface UserService {

    @GET("/users")
    suspend fun getUsers() : List<User>
}