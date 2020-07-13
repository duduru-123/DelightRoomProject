package com.delightroom.android.gitproject.datasource.vo

data class UserDetailVO(
    var id: Long,
    var login: String,
    var thumbUrl: String,
    var name: String,
    var bio: String,
    var followingCount: Long = 0L,
    var followersCount: Long = 0L,
    var blogUrl: String,
    var company: String,
    var location: String
)