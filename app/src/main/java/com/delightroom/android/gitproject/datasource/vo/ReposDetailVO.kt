package com.delightroom.android.gitproject.datasource.vo

data class ReposDetailVO (
    var id: Long,
    var name: String,
    var userLogin: String,
    var size: Long = 0L,
    var starsCount: Long=0L,
    var forksCount: Long=0L,
    var watchersCount: Long = 0L,
    var private: Boolean = false,
    var updateTime: Long = 0L,
    var userThumbUrl: String,
    var hasIssues: Boolean,
    var hasProjects: Boolean,
    var hasDownloads: Boolean,
    var hasWiki: Boolean,
    var hasPages: Boolean,
    var licenseKey: String,
    var licenseName: String,
    var licenseUrl: String,
    var languagesUrl:String,
    var languages: List<String>? = null,
    var htmlUrl: String
)