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
    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }


    /**
     * exception handler
     */
    private fun getExceptionHandler() = CoroutineExceptionHandler { _, exception ->
        logE(exception.message)

        isLoading.postValue(false)

        exception.message?.let {
            val message = if (it.contains("403")) {
                context.getString(R.string.rate_limit_exceeded)
            } else {
                context.getString(R.string.no_data)
            }

            toast.postValue(message)
        }
    }
}