package com.delightroom.android.gitproject.datasource.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReposVO(
    @PrimaryKey
    var id: Long,
    var name: String,
    var userLogin: String,
    var size: Long,
    val language: String,
    var starsCount: Long,
    var forksCount: Long,
    var isPrivate: Boolean,
    var updateTime: Long
)