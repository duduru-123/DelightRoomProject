package com.delightroom.android.gitproject.present.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.delightroom.android.gitproject.datasource.paging.UsersDataSource
import com.delightroom.android.gitproject.datasource.vo.UserVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.repository.UserRepository

class UsersViewModel(
    private val retrofitManager: RetrofitManager,
    private val userRepository: UserRepository
): ViewModel() {



    /**
     * create live data
     */
    private fun createListOFUserVOLiveData(songId: String): LiveData<PagedList<UserVO>> {
        val pageSize = 20
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(pageSize + 10)
            .setPageSize(pageSize)
            .setPrefetchDistance(10)
            .build()

        return LivePagedListBuilder(object : DataSource.Factory<Int, UserVO>() {
            override fun create(): DataSource<Int, UserVO> {
                return UsersDataSource(retrofitManager, viewModelScope)
            }
        }, config).build()
    }
}