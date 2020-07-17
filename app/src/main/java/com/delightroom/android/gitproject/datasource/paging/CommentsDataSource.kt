package com.delightroom.android.gitproject.datasource.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.delightroom.android.gitproject.datasource.remote.api.ReposService
import com.delightroom.android.gitproject.datasource.vo.CommentVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.utility.convertToCommentVO
import com.delightroom.android.gitproject.utility.logI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CommentsDataSource(
    retrofitManager: RetrofitManager,
    private val scope: CoroutineScope,
    private val userLogin: String,
    private val reposName: String,
    private val isLoading: MutableLiveData<Boolean>
) : PageKeyedDataSource<Int, CommentVO>() {

    private val reposApi = retrofitManager.createApi(ReposService::class.java)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CommentVO>
    ) {
        scope.launch {
            isLoading.postValue(true)

            val currentPage = 0
            val pageSize = params.requestedLoadSize
            val result = reposApi.getComments(
                userLogin = userLogin,
                reposName = reposName,
                page = currentPage,
                pageSize = pageSize
            )
            val listOfCommentVO = result.map { it.convertToCommentVO() }.toList()
            val nextPage = currentPage + 1

            logI("loadInitial size: ${listOfCommentVO.size}")
            callback.onResult(listOfCommentVO, null, nextPage)

            isLoading.postValue(false)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CommentVO>
    ) {
        scope.launch {
            isLoading.postValue(true)

            val currentPage = params.key
            val pageSize = params.requestedLoadSize
            val result = reposApi.getComments(
                userLogin = userLogin,
                reposName = reposName,
                page = currentPage,
                pageSize = pageSize
            )
            val listOfCommentVO = result.map { it.convertToCommentVO() }.toList()
            val nextPage = currentPage + 1

            logI("loadAfter size: ${listOfCommentVO.size}")

            callback.onResult(listOfCommentVO, nextPage)

            isLoading.postValue(false)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CommentVO>
    ) {
        scope.launch {
        }
    }
}