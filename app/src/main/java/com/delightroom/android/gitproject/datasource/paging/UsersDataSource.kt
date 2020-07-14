package com.delightroom.android.gitproject.datasource.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.delightroom.android.gitproject.datasource.remote.api.UserService
import com.delightroom.android.gitproject.datasource.vo.UserVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.utility.convertToUserVO
import com.delightroom.android.gitproject.utility.logE
import com.delightroom.android.gitproject.utility.logI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersDataSource(
    retrofitManager: RetrofitManager,
    private val viewModelScope: CoroutineScope,
    private val isLoading: MutableLiveData<Boolean>
) : PageKeyedDataSource<Int, UserVO>() {

    private val userApi = retrofitManager.createApi(UserService::class.java)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, UserVO>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)

            try {
                val currentPage = 0
                val pageSize = params.requestedLoadSize
                val result = userApi.getUsers(page = currentPage, pageSize = pageSize)
                val listOfUserVO = result.map { it.convertToUserVO() }.toList()
                val nextPage = currentPage + 1

                logI("loadInitial size: ${listOfUserVO.size}")
                callback.onResult(listOfUserVO, null, nextPage)

            } catch (e: Exception) {

                logE(e.message)
            }

            isLoading.postValue(false)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, UserVO>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)

            try {
                val currentPage = params.key
                val pageSize = params.requestedLoadSize
                val result = userApi.getUsers(page = currentPage, pageSize = pageSize)
                val listOfUserVO = result.map { it.convertToUserVO() }.toList()
                val nextPage = currentPage + 1

                logI("loadAfter size: ${listOfUserVO.size}")

                callback.onResult(listOfUserVO, nextPage)
            } catch (e: Exception) {
                logE(e.message)
            }

            isLoading.postValue(false)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, UserVO>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
            } catch (e: Exception) {
                logE(e.message)
            }
        }
    }
}