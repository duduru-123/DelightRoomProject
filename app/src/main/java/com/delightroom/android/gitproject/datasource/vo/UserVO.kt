package com.delightroom.android.gitproject.datasource.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserVO (
    @PrimaryKey
    var id: Long,
    var login: String,
    var thumbUrl: String
)