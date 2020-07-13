package com.delightroom.android.gitproject.datasource.vo

data class ReposVO (
    var id: Long,
    var name: String,
    var size: Long = 0L,
    val language: String,
    var startCount: Long=0L,
    var forksCount: Long=0L,
    var private: Boolean = false
)