package com.delightroom.android.gitproject.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.delightroom.android.gitproject.datasource.vo.UserVO


/**
 * UserVO Dao
 *
 * for managing UserVO
 */
@Dao
interface UserVODao {

    /**
     * insert userVO
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserVO(userVO: UserVO)


    /**
     * update userVO
     */
    @Update
    fun updateUserVO(userVO: UserVO)


    /**
     * delete userVO
     */
    @Delete
    fun deleteUserVO(userVO: UserVO)


    /**
     * select all userVO
     */
    @Query("SELECT * FROM UserVO")
    fun selectAllUserVO(): LiveData<List<UserVO>>
}