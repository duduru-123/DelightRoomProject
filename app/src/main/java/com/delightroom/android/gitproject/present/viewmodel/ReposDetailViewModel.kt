package com.delightroom.android.gitproject.present.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.delightroom.android.gitproject.datasource.paging.CommentsDataSource
import com.delightroom.android.gitproject.datasource.vo.CommentVO
import com.delightroom.android.gitproject.datasource.vo.ReposDetailVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.repository.ReposInterface
import com.delightroom.android.gitproject.utility.logI
import kotlinx.coroutines.launch

class ReposDetailViewModel(
    private val context: Context,
    private val reposInterface: ReposInterface,
    private val retrofitManager: RetrofitManager
) : BaseViewModel(context) {

    var userLogin: String = ""
    var reposName: String = ""
    var reposDetailVO = MutableLiveData<ReposDetailVO>()
    var languages = MutableLiveData<String>()
    val listOfCommentVO = createListOfCommentVOLiveData()


    /**
     * set data
     */
    fun setData(userLogin: String, reposName: String) {
        this.userLogin = userLogin
        this.reposName = reposName

        scope.launch {
            val result = reposInterface.requestUserRepository(userLogin, reposName)
            reposDetailVO.postValue(result)

            val languagesUrl = result.languagesUrl

            if (languagesUrl != "") {
                val languageResult = reposInterface.requestLanguages(userLogin, reposName)
                updateLanguages(languageResult)
            }

            logI("result: $result")
        }
    }


    /**
     * update languages
     */
    private fun updateLanguages(languageResult: Map<String, Int>) {
        val languages = if (languageResult.isEmpty()) {
            ""
        } else {
            val listOfLanguage = languageResult.toList()
            val totalSize = listOfLanguage.sumBy { it.second }
            var result = ""
            listOfLanguage.forEach {
                val rate = it.second.toFloat() / totalSize.toFloat() * 100f
                result += "${it.first}: ${String.format("%.1f", rate)}%"

                if (listOfLanguage.last() != it) result += ",  "
            }

            result
        }

        this.languages.postValue(languages)
    }


    /**
     * refresh list of commentVO
     */
    fun refreshListOfCommentVO() {
        listOfCommentVO.value?.dataSource?.invalidate()
    }


    /**
     * create comments live data
     */
    private fun createListOfCommentVOLiveData(): LiveData<PagedList<CommentVO>> {
        val pageSize = 20
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(pageSize + 10)
            .setPageSize(pageSize)
            .setPrefetchDistance(10)
            .build()

        return LivePagedListBuilder(object : DataSource.Factory<Int, CommentVO>() {
            override fun create(): DataSource<Int, CommentVO> {
                return CommentsDataSource(
                    retrofitManager, scope, userLogin, reposName, isLoading
                )
            }
        }, config).build()
    }
}