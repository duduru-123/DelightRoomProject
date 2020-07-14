package com.delightroom.android.gitproject.present.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delightroom.android.gitproject.datasource.vo.ReposDetailVO
import com.delightroom.android.gitproject.manager.RetrofitManager
import com.delightroom.android.gitproject.repository.UserRepository
import com.delightroom.android.gitproject.utility.logE
import com.delightroom.android.gitproject.utility.logI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReposDetailViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var userLogin: String = ""
    var reposName: String = ""
    var reposDetailVO = MutableLiveData<ReposDetailVO>()
    var languages = MutableLiveData<String>()


    /**
     * set data
     */
    fun setData(userLogin: String, reposName: String) {
        this.userLogin = userLogin
        this.reposName = reposName

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = userRepository.requestUserRepository(userLogin, reposName)
                reposDetailVO.postValue(result)

                val languagesUrl = result.languagesUrl

                if (languagesUrl != "") {
                    val languageResult = userRepository.requestLanguages(userLogin, reposName)
                    updateLanguages(languageResult)
                }

                logI("result: $result")

            } catch (e: Exception) {
                logE(e.message)
            }
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
}