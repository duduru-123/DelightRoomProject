package com.delightroom.android.gitproject.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * 사진
 */
@Entity
data class AppData(
    @PrimaryKey
    var id: String = "id", //아이디
    var value: String = "" //앱 데이터

) : Serializable