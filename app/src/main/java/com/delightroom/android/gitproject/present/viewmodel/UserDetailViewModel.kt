package com.delightroom.android.gitproject.present.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.delightroom.android.gitproject.datasource.paging.StarredDataSource
import com.delightroom.android.gitproject.datasource.vo.ReposVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.repository.UserRepository

class UserDetailViewModel(
    private val retrofitManager: RetrofitManager,
    private val userRepository: UserRepository
) : ViewModel() {

    var userId: String = ""

    // list of reposVO
    lateinit var listOfStarredReposVO: LiveData<PagedList<ReposVO>>


    /**
     * set data
     */
    fun setData(userId: String) {
        this.userId = userId

        listOfStarredReposVO = createListOfStarredReposVOLiveData()
    }


    /**
     * create live data
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
     * refresh list of starred reposVO
     */
    fun refreshListOfStarredReposVO() {
        listOfStarredReposVO.value?.dataSource?.invalidate()
    }
}