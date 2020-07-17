package com.delightroom.android.gitproject.datasource.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.delightroom.android.gitproject.common.Config
import com.delightroom.android.gitproject.datasource.local.db.AppDatabase
import com.delightroom.android.gitproject.datasource.local.model.AppData
import com.delightroom.android.gitproject.datasource.remote.api.UserService
import com.delightroom.android.gitproject.datasource.vo.ReposVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.utility.convertToReposVO
import com.delightroom.android.gitproject.utility.logI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UserReposDataSource(
    retrofitManager: RetrofitManager,
    database: AppDatabase,
    private val scope: CoroutineScope,
    private val userId: String,
    private val isLoading: MutableLiveData<Boolean>
) : PageKeyedDataSource<Int, ReposVO>() {

    private val userApi = retrofitManager.createApi(UserService::class.java)
    private val appDao = database.appDataDao()
    private val reposVODao = database.reposVODao()
    var isRefresh = false

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ReposVO>
    ) {
        scope.launch {
            isLoading.postValue(true)

            if (isRefresh) {
                val currentPage = 0
                val pageSize = params.requestedLoadSize
                val result =
                    userApi.getUserRepos(userId = userId, page = currentPage, pageSize = pageSize)
                val listOfUserVO = result.map { it.convertToReposVO() }.toList()
                val nextPage = currentPage + 1

                appDao.insertAppData(
                    AppData(
                        Config.AppData.STARRED_REPOS_CURRENT_PAGE,
                        currentPage.toString()
                    )
                )
                reposVODao.insertReposVO(*listOfUserVO.toTypedArray())


                logI("loadInitial size: ${listOfUserVO.size}")
                callback.onResult(listOfUserVO, null, nextPage)

            } else {
                logI("load users int local")
                val currentPage =
                    appDao.selectAppDataById(Config.AppData.STARRED_REPOS_CURRENT_PAGE).value?.toInt()
                        ?: 0
                val nextPage = currentPage + 1
                val listOfReposVO = reposVODao.selectAllReposVO()

                logI("loadInitial local size: ${listOfReposVO.size}")
                callback.onResult(listOfReposVO, null, nextPage)
            }

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
            val result =
                userApi.getUserRepos(userId = userId, page = currentPage, pageSize = pageSize)
            val listOfReposVO = result.map { it.convertToReposVO() }.toList()
            val nextPage = currentPage + 1

            appDao.insertAppData(
                AppData(
                    Config.AppData.STARRED_REPOS_CURRENT_PAGE,
                    currentPage.toString()
                )
            )
            reposVODao.insertReposVO(*listOfReposVO.toTypedArray())

            val localsize = reposVODao.selectAllReposVO()
            logI("loadAfter size: ${listOfReposVO.size}")
            logI("loadAfter localsize size: ${localsize.size}")

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