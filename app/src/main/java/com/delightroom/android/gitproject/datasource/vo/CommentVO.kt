package com.delightroom.android.gitproject.datasource.vo

data class CommentVO (
    var id: Long,
    var body: String,
    var updateTime: Long = 0L
)