package com.delightroom.android.gitproject.utility

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.delightroom.android.gitproject.BuildConfig
import com.delightroom.android.gitproject.datasource.remote.model.Comment
import com.delightroom.android.gitproject.datasource.remote.model.User
import com.delightroom.android.gitproject.datasource.remote.model.UserDetail
import com.delightroom.android.gitproject.datasource.remote.model.UserRepos
import com.delightroom.android.gitproject.datasource.vo.*


/**
 * object converting
 */

/**
 * convert User to UserVO
 */
fun User.convertToUserVO(): UserVO {
    return UserVO(
        id = this.id ?: 0,
        login = this.login ?: "",
        thumbUrl = this.avatarURL ?: ""
    )
}


/**
 * convert UserDetail to UserDetailVO
 */
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


/**
 * convert UserRepos to ReposVO
 */
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
        isPrivate = this.private ?: false,
        userLogin = this.owner.login ?: "",
        updateTime = updateDate
    )
}


/**
 * convert UserRepos to ReposDetailVO
 */
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
        htmlUrl = this.htmlURL ?: "",
        description = this.description ?: "",
        gitUrl = this.gitURL ?: "",
        sshUrl = this.sshURL ?: "",
        cloneUrl = this.cloneURL ?: "",
        svnUrl = this.svnURL ?: "",
        homepage = this.homepage ?: ""
    )
}


/**
 * convert Comment to CommentVO
 */
fun Comment.convertToCommentVO(): CommentVO {
    val updateAt = this.createdAt
    val updateDate = if (updateAt == null) {
        0L
    } else {
        getDateMillis(updateAt)
    }

    return CommentVO(
        id = this.id ?: 0L,
        body = this.body ?: "",
        updateTime = updateDate,
        userLogin = this.user?.login ?: ""
    )
}


/**
 * logging
 */


/**
 * log i
 * @param message nullable
 * @param distribution
 */
fun Any.logI(message: String?, distribution: String = "@@@@@@@@") {
    if (BuildConfig.DEBUG)
        Log.i(this::class.java.simpleName, "$distribution $message")
}


/**
 * log e
 * @param message nullable
 * @param distribution
 */
fun Any.logE(message: String?, distribution: String = "@@@@@@@@") {
    if (BuildConfig.DEBUG)
        Log.e(this::class.java.simpleName, "$distribution $message")
}


/**
 * show toast
 * @param message
 */
fun Activity.showToast(message: String?) {
    Toast.makeText(this.applicationContext, message, Toast.LENGTH_SHORT).show()
}
