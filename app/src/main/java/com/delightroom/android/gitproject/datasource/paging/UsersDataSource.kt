package com.delightroom.android.gitproject.datasource.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.delightroom.android.gitproject.datasource.remote.api.UserService
import com.delightroom.android.gitproject.datasource.vo.UserVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.utility.convertToUserVO
import com.delightroom.android.gitproject.utility.logI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UsersDataSource(
    retrofitManager: RetrofitManager,
    private val scope: CoroutineScope,
    private val isLoading: MutableLiveData<Boolean>
) : PageKeyedDataSource<Int, UserVO>() {

    private val userApi = retrofitManager.createApi(UserService::class.java)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, UserVO>
    ) {
        scope.launch {
            isLoading.postValue(true)

            logI("load users int remote")
            val currentPage = 0
            val pageSize = params.requestedLoadSize
            val result = userApi.getUsers(page = currentPage, pageSize = pageSize)
            val listOfUserVO = result.map { it.convertToUserVO() }.toList()
            val nextPage = currentPage + 1

            logI("loadInitial remote size: ${listOfUserVO.size}")
            callback.onResult(listOfUserVO, null, nextPage)

            isLoading.postValue(false)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, UserVO>
    ) {
        scope.launch {
            isLoading.postValue(true)

            val currentPage = params.key
            val pageSize = params.requestedLoadSize
            val result = userApi.getUsers(page = currentPage, pageSize = pageSize)
            val listOfUserVO = result.map { it.convertToUserVO() }.toList()
            val nextPage = currentPage + 1

            callback.onResult(listOfUserVO, nextPage)

            isLoading.postValue(false)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, UserVO>
    ) {
        scope.launch {
        }
    }
}