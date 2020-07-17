package com.delightroom.android.gitproject.datasource.local.dao

import androidx.room.*
import com.delightroom.android.gitproject.datasource.vo.ReposVO


/**
 * ReposVO Dao
 *
 * for managing ReposVO
 */
@Dao
interface ReposVODao {

    /**
     * insert ReposVO
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReposVO(vararg reposVO: ReposVO)


    /**
     * update ReposVO
     */
    @Update
    fun updateReposVO(reposVO: ReposVO)


    /**
     * delete ReposVO
     */
    @Delete
    fun deleteReposVO(reposVO: ReposVO)


    /**
     * select all ReposVO
     */
    @Query("SELECT * FROM ReposVO")
    fun selectAllReposVO(): List<ReposVO>
}