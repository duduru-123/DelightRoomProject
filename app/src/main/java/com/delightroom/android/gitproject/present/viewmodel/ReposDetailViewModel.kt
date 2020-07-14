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

    var reposDetailVO = MutableLiveData<ReposDetailVO>()


    /**
     * set data
     */
    fun setData(userLogin: String, reposName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = userRepository.requestUserRepository(userLogin, reposName)
                reposDetailVO.postValue(result)

                logI("result: $result")

            } catch (e: Exception) {
                logE(e.message)
            }
        }
    }
}