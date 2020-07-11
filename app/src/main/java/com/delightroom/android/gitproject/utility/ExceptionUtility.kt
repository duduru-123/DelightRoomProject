package com.delightroom.android.gitproject.utility

import android.util.Log
import android.view.View



fun View.show() {
    this.visibility = View.VISIBLE
}
fun View.hide() {
    this.visibility = View.INVISIBLE
}
fun View.gone() {
    this.visibility = View.GONE
}


fun Any.logI(message: String?, distribution: String = "@@@@@@@@") {
    Log.i(distribution, message)
}