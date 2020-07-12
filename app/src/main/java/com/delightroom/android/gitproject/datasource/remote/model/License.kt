package com.delightroom.android.gitproject.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class License (
    val key: String?,
    val name: String?,

    @SerializedName("spdx_id")
    val spdxID: String?,

    val url: String?,

    @SerializedName("node_id")
    val nodeID: String?
)
