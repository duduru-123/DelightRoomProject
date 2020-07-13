package com.delightroom.android.gitproject.datasource.vo

data class ReposVO (
    var id: Long,
    var name: String,
    var userLogin: String,
    var size: Long = 0L,
    val language: String,
    var starsCount: Long=0L,
    var forksCount: Long=0L,
    var private: Boolean = false,
    var updateTime: Long = 0L
)