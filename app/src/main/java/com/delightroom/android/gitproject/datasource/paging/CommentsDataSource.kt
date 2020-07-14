package com.delightroom.android.gitproject.datasource.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.delightroom.android.gitproject.datasource.remote.api.UserService
import com.delightroom.android.gitproject.datasource.vo.CommentVO
import com.delightroom.android.gitproject.datasource.vo.ReposVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.utility.convertToCommentVO
import com.delightroom.android.gitproject.utility.convertToReposVO
import com.delightroom.android.gitproject.utility.logE
import com.delightroom.android.gitproject.utility.logI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentsDataSource(
    retrofitManager: RetrofitManager,
    private val viewModelScope: CoroutineScope,
    private val userLogin: String,
    private val reposName: String,
    private val isLoading: MutableLiveData<Boolean>
) : PageKeyedDataSource<Int, CommentVO>() {

    private val apiInterFace = retrofitManager.createApi(UserService::class.java)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CommentVO>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)

            try {
                val currentPage = 0
                val pageSize = params.requestedLoadSize
                val result = apiInterFace.getComments(
                    userLogin = userLogin,
                    reposName = reposName,
                    page = currentPage,
                    pageSize = pageSize
                )
                val listOfConvertVO = result.map { it.convertToCommentVO() }.toList()
                val nextPage = currentPage + 1

                logI("loadInitial size: ${listOfConvertVO.size}")
                callback.onResult(listOfConvertVO, null, nextPage)

            } catch (e: Exception) {
                logE(e.message)
            }

            isLoading.postValue(false)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CommentVO>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)

            try {
                val currentPage = params.key
                val pageSize = params.requestedLoadSize
                val result = apiInterFace.getComments(
                    userLogin = userLogin,
                    reposName = reposName,
                    page = currentPage,
                    pageSize = pageSize
                )
                val listOfConvertVO = result.map { it.convertToCommentVO() }.toList()
                val nextPage = currentPage + 1

                logI("loadAfter size: ${listOfConvertVO.size}")

                callback.onResult(listOfConvertVO, nextPage)
            } catch (e: Exception) {
                logE(e.message)
            }

            isLoading.postValue(false)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CommentVO>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
            } catch (e: Exception) {
                logE(e.message)
            }
        }
    }
}