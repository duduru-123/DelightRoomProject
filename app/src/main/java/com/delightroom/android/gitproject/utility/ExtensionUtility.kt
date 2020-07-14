package com.delightroom.android.gitproject.utility

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.delightroom.android.gitproject.datasource.remote.model.User
import com.delightroom.android.gitproject.datasource.remote.model.UserDetail
import com.delightroom.android.gitproject.datasource.remote.model.UserRepos
import com.delightroom.android.gitproject.datasource.vo.ReposDetailVO
import com.delightroom.android.gitproject.datasource.vo.ReposVO
import com.delightroom.android.gitproject.datasource.vo.UserDetailVO
import com.delightroom.android.gitproject.datasource.vo.UserVO


/**
 * object converting
 */
fun User.convertToUserVO(): UserVO {
    return UserVO(
        id = this.id ?: 0,
        login = this.login ?: "",
        thumbUrl = this.avatarURL ?: ""
    )
}

fun UserDetail.convertToUserDetailVO(): UserDetailVO {
    return UserDetailVO(
        id = this.id ?: 0,
        login = this.login ?: "",
        thumbUrl = this.avatarURL ?: "",
        bio = this.bio ?: "",
        name = this.name ?: "",
        followersCount = this.followers ?: 0L,
        followingCount = this.following ?: 0L,
        blogUrl = this.blog ?: "",
        company = this.company ?: "",
        location = this.location ?: ""
    )
}

fun UserRepos.convertToReposVO(): ReposVO {
    val updateAt = this.createdAt
    val updateDate = if (updateAt == null) {
        0L
    } else {
        getDateMillis(updateAt)
    }

    return ReposVO(
        id = this.id ?: 0L,
        size = this.size ?: 0L,
        name = this.name ?: "",
        language = this.language ?: "",
        starsCount = this.stargazersCount ?: 0L,
        forksCount = this.forksCount ?: 0L,
        private = this.private ?: false,
        userLogin = this.owner.login ?: "",
        updateTime = updateDate
    )
}

fun UserRepos.convertToReposDetailVO(): ReposDetailVO {
    val updateAt = this.createdAt
    val updateDate = if (updateAt == null) {
        0L
    } else {
        getDateMillis(updateAt)
    }

    return ReposDetailVO(
        id = this.id ?: 0L,
        size = this.size ?: 0L,
        name = this.name ?: "",
        starsCount = this.stargazersCount ?: 0L,
        forksCount = this.forksCount ?: 0L,
        private = this.private ?: false,
        userLogin = this.owner.login ?: "",
        updateTime = updateDate,
        userThumbUrl = this.owner.avatarURL ?: "",
        hasDownloads = this.hasDownloads ?: false,
        hasIssues = this.hasIssues ?: false,
        hasPages = this.hasPages ?: false,
        hasProjects = this.hasProjects ?: false,
        hasWiki = this.hasWiki ?: false,
        watchersCount = this.watchersCount ?: 0L,
        licenseKey = this.license?.key ?: "",
        licenseName = this.license?.name ?: "",
        licenseUrl = this.license?.url ?: "",
        languagesUrl = this.languagesURL ?: "",
        htmlUrl = this.htmlURL ?: ""
    )
}


/**
 * visibility
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}


/**
 * log
 */
fun Any.logI(message: String?, distribution: String = "@@@@@@@@") {
    Log.i(this::class.java.simpleName, "$distribution $message")
}

fun Any.logE(message: String?, distribution: String = "@@@@@@@@") {
    Log.e(this::class.java.simpleName, "$distribution $message")
}


/**
 * show toast
 */
fun Activity.showToast(message: String) {
    Toast.makeText(this.applicationContext, message, Toast.LENGTH_SHORT).show()
}
