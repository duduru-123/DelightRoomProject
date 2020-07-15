package com.delightroom.android.gitproject.present.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.delightroom.android.gitproject.datasource.paging.StarredDataSource
import com.delightroom.android.gitproject.datasource.paging.UserReposDataSource
import com.delightroom.android.gitproject.datasource.vo.ReposVO
import com.delightroom.android.gitproject.datasource.vo.UserDetailVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.repository.UserRepository
import com.delightroom.android.gitproject.utility.logI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val context: Context,
    private val retrofitManager: RetrofitManager,
    private val userRepository: UserRepository
) : BaseViewModel(context) {

    var userLogin: String = ""

    // list of reposVO
    var userDetailVO = MutableLiveData<UserDetailVO>()
    lateinit var listOfStarredReposVO: LiveData<PagedList<ReposVO>>
    lateinit var listOfUserReposReposVO: LiveData<PagedList<ReposVO>>
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    var starredOffsetY = 0
    var userReposOffsetY = 0


    /**
     * set data
     */
    fun setData(userId: String) {
        this.userLogin = userId

        listOfStarredReposVO = createListOfStarredReposVOLiveData()
        listOfUserReposReposVO = createListOfUserReposReposVOLiveData()
    }


    /**
     * request user detail vo
     */
    fun requestUserDetail() {
        scope.launch(Dispatchers.IO) {
            val result = userRepository.requestUser(userLogin)
            userDetailVO.postValue(result)

            logI("userDetailVO: $result")
        }
    }


    /**
     * create starred live data
     */
    private fun createListOfStarredReposVOLiveData(): LiveData<PagedList<ReposVO>> {
        val pageSize = 20
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(pageSize + 10)
            .setPageSize(pageSize)
            .setPrefetchDistance(10)
            .build()

        return LivePagedListBuilder(object : DataSource.Factory<Int, ReposVO>() {
            override fun create(): DataSource<Int, ReposVO> {
                return StarredDataSource(retrofitManager, scope, userLogin, isLoading)
            }
        }, config).build()
    }


    /**
     * create user repos live data
     */
    private fun createListOfUserReposReposVOLiveData(): LiveData<PagedList<ReposVO>> {
        val pageSize = 20
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(pageSize + 10)
            .setPageSize(pageSize)
            .setPrefetchDistance(10)
            .build()

        return LivePagedListBuilder(object : DataSource.Factory<Int, ReposVO>() {
            override fun create(): DataSource<Int, ReposVO> {
                return UserReposDataSource(retrofitManager, scope, userLogin, isLoading)
            }
        }, config).build()
    }


    /**
     * refresh list of starred reposVO
     */
    fun refreshListOfStarredReposVO() {
        listOfStarredReposVO.value?.dataSource?.invalidate()
    }


    /**
     * refresh list of user repos reposVO
     */
    fun refreshListOfUserReposReposVO() {
        listOfUserReposReposVO.value?.dataSource?.invalidate()
    }
}