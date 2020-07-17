package com.delightroom.android.gitproject.datasource.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.delightroom.android.gitproject.datasource.remote.api.UserService
import com.delightroom.android.gitproject.datasource.vo.ReposVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.utility.convertToReposVO
import com.delightroom.android.gitproject.utility.logI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class StarredDataSource(
    retrofitManager: RetrofitManager,
    private val scope: CoroutineScope,
    private val userId: String,
    private val isLoading: MutableLiveData<Boolean>
) : PageKeyedDataSource<Int, ReposVO>() {

    private val userApi = retrofitManager.createApi(UserService::class.java)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ReposVO>
    ) {
        scope.launch {
            isLoading.postValue(true)

            val currentPage = 0
            val pageSize = params.requestedLoadSize
            val result = userApi.getUserStarredRepos(
                userId = userId,
                page = currentPage,
                pageSize = pageSize
            )
            val listOfReposVO = result.map { it.convertToReposVO() }.toList()
            val nextPage = currentPage + 1

            logI("loadInitial size: ${listOfReposVO.size}")
            callback.onResult(listOfReposVO, null, nextPage)

            isLoading.postValue(false)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ReposVO>
    ) {
        scope.launch {
            isLoading.postValue(true)

            val currentPage = params.key
            val pageSize = params.requestedLoadSize
            val result = userApi.getUserStarredRepos(
                userId = userId,
                page = currentPage,
                pageSize = pageSize
            )
            val listOfReposVO = result.map { it.convertToReposVO() }.toList()
            val nextPage = currentPage + 1

            logI("loadAfter size: ${listOfReposVO.size}")

            callback.onResult(listOfReposVO, nextPage)

            isLoading.postValue(false)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ReposVO>
    ) {
        scope.launch {
        }
    }
}