package com.delightroom.android.gitproject.datasource.paging

import androidx.paging.PageKeyedDataSource
import com.delightroom.android.gitproject.datasource.remote.api.UserService
import com.delightroom.android.gitproject.datasource.vo.ReposVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.utility.convertToReposVO
import com.delightroom.android.gitproject.utility.logE
import com.delightroom.android.gitproject.utility.logI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserReposDataSource(
    retrofitManager: RetrofitManager,
    private val viewModelScope: CoroutineScope,
    private val userId: String
) : PageKeyedDataSource<Int, ReposVO>() {

    private val apiInterFace = retrofitManager.createApi(UserService::class.java)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ReposVO>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currentPage = 0
                val pageSize = params.requestedLoadSize
                val result = apiInterFace.getUserRepos(
                    userId = userId,
                    page = currentPage,
                    pageSize = pageSize
                )
                val listOfUserVO = result.map { it.convertToReposVO() }.toList()
                val nextPage = currentPage + 1

                logI("loadInitial size: ${listOfUserVO.size}")
                callback.onResult(listOfUserVO, null, nextPage)

            } catch (e: Exception) {

                logE(e.message)
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ReposVO>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currentPage = params.key
                val pageSize = params.requestedLoadSize
                val result = apiInterFace.getUserRepos(
                    userId = userId,
                    page = currentPage,
                    pageSize = pageSize
                )
                val listOfUserVO = result.map { it.convertToReposVO() }.toList()
                val nextPage = currentPage + 1

                logI("loadAfter size: ${listOfUserVO.size}")

                callback.onResult(listOfUserVO, nextPage)
            } catch (e: Exception) {
                logE(e.message)
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ReposVO>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
            } catch (e: Exception) {
                logE(e.message)
            }
        }
    }
}