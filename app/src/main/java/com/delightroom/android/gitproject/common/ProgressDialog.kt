package com.delightroom.android.gitproject.common

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.delightroom.android.gitproject.R


class ProgressDialog(context: Context) : Dialog(context, R.style.Transparent) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_progress)
        setCanceledOnTouchOutside(false)
    }
}