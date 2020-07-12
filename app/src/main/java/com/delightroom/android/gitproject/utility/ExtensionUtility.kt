package com.delightroom.android.gitproject.utility

import android.util.Log
import android.view.View
import com.delightroom.android.gitproject.datasource.remote.model.User
import com.delightroom.android.gitproject.datasource.remote.model.UserRepos
import com.delightroom.android.gitproject.datasource.vo.ReposVO
import com.delightroom.android.gitproject.datasource.vo.UserVO


/**
 * object converting
 */
fun User.convertToUserVO(): UserVO {
    return UserVO(
        id = this.login ?: ""
    )
}
fun UserRepos.convertToReposVO(): ReposVO {
    return ReposVO(
        id = this.description ?: ""
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