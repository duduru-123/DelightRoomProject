package com.delightroom.android.gitproject.present.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.delightroom.android.gitproject.datasource.paging.RepositoryDataSource
import com.delightroom.android.gitproject.datasource.paging.UsersDataSource
import com.delightroom.android.gitproject.datasource.vo.ReposVO
import com.delightroom.android.gitproject.datasource.vo.UserVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.repository.UserRepository

class UsersViewModel(
    private val context: Context,
    private val retrofitManager: RetrofitManager,
    private val userRepository: UserRepository
) : BaseViewModel(context) {

    // list of userVO
    val listOfUserVO = createListOfUserVOLiveData()
    val listOfRepositoryReposVO = createListOfReposVOLiveData()
    val isLoading = MutableLiveData<Boolean>().apply { value = false }


    /**
     * refresh list of userVO
     */
    fun refreshListOfUserVO() {
        listOfUserVO.value?.dataSource?.invalidate()
    }

    /**
     * refresh list of ReposVO
     */
    fun refreshListOfReposVO() {
        listOfRepositoryReposVO.value?.dataSource?.invalidate()
    }


    /**
     * create live data from Users
     */
    private fun createListOfUserVOLiveData(): LiveData<PagedList<UserVO>> {
        val pageSize = 20
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(pageSize + 10)
            .setPageSize(pageSize)
            .setPrefetchDistance(10)
            .build()

        return LivePagedListBuilder(object : DataSource.Factory<Int, UserVO>() {
            override fun create(): DataSource<Int, UserVO> {
                return UsersDataSource(retrofitManager, scope, isLoading)
            }
        }, config).build()
    }


    /**
     * create list of reposVO from Repository
     */
    private fun createListOfReposVOLiveData(): LiveData<PagedList<ReposVO>> {
        val pageSize = 20
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(pageSize + 10)
            .setPageSize(pageSize)
            .setPrefetchDistance(10)
            .build()

        return LivePagedListBuilder(object : DataSource.Factory<Int, ReposVO>() {
            override fun create(): DataSource<Int, ReposVO> {
                return RepositoryDataSource(retrofitManager, scope, isLoading)
            }
        }, config).build()
    }
}