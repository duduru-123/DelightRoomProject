package com.delightroom.android.gitproject.present.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.delightroom.android.gitproject.R
import com.delightroom.android.gitproject.utility.logE
import kotlinx.coroutines.*

open class BaseViewModel(private val context: Context) : ViewModel() {

    val scope = CoroutineScope(Dispatchers.IO + getExceptionHandler())
    val toast = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }


    /**
     * exception handler
     */
    private fun getExceptionHandler() = CoroutineExceptionHandler { _, exception ->
        logE(exception.message)

        val message = context.getString(R.string.no_data)
        toast.postValue(message)
    }
}