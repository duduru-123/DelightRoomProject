package com.delightroom.android.gitproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import com.delightroom.android.gitproject.common.ProgressDialog

open class BaseActivity: AppCompatActivity() {
    private val progressDialog by lazy {

        ProgressDialog(this)
    }


    /**
     * show progress
     */
    fun showProgress() {
        if (!progressDialog.isShowing) progressDialog.show()
    }

    /**
     * hide progress
     */
    fun hideProgress() {
        if (progressDialog.isShowing) progressDialog.dismiss()
    }
}