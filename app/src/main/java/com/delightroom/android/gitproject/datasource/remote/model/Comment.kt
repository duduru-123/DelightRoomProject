package com.delightroom.android.gitproject.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class Comment(
    val url: String?,

    @SerializedName("html_url")
    val htmlURL: String?,

    val id: Long?,

    @SerializedName("node_id")
    val nodeID: String?,

    val user: User?,
    val position: Any? = null,
    val line: Any? = null,
    val path: Any? = null,

    @SerializedName("commit_id")
    val commitID: String?,

    @SerializedName("created_at")
    val createdAt: String?,

    @SerializedName("updated_at")
    val updatedAt: String?,

    @SerializedName("author_association")
    val authorAssociation: String?,

    val body: String?
)