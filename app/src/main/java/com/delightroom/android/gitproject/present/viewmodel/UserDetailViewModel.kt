package com.delightroom.android.gitproject.present.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.delightroom.android.gitproject.datasource.paging.StarredDataSource
import com.delightroom.android.gitproject.datasource.paging.UserReposDataSource
import com.delightroom.android.gitproject.datasource.vo.ReposVO
import com.delightroom.android.gitproject.datasource.vo.UserDetailVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.repository.UserRepository
import com.delightroom.android.gitproject.utility.logE
import com.delightroom.android.gitproject.utility.logI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val retrofitManager: RetrofitManager,
    private val userRepository: UserRepository
) : ViewModel() {

    var userId: String = ""

    // list of reposVO
    var userDetailVO = MutableLiveData<UserDetailVO>()
    lateinit var listOfStarredReposVO: LiveData<PagedList<ReposVO>>
    lateinit var listOfUserReposReposVO: LiveData<PagedList<ReposVO>>


    /**
     * set data
     */
    fun setData(userId: String) {
        this.userId = userId

        listOfStarredReposVO = createListOfStarredReposVOLiveData()
        listOfUserReposReposVO = createListOfUserReposReposVOLiveData()
    }


    /**
     * request user detail vo
     */
    fun requestUserDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = userRepository.requestUser(userId)
                userDetailVO.postValue(result)
                logI("userDetailVO: $result")

            } catch (e: Exception) {
                logE(e.message)
            }
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
                return StarredDataSource(retrofitManager, viewModelScope, userId)
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
                return UserReposDataSource(retrofitManager, viewModelScope, userId)
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