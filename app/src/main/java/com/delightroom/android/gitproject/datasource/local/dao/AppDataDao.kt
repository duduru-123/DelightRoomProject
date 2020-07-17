package com.delightroom.android.gitproject.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.delightroom.android.gitproject.datasource.local.model.AppData


/**
 * AppData Dao
 *
 * for managing app data
 */
@Dao
interface AppDataDao {

    /**
     * insert appData
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAppData(appData: AppData)


    /**
     * update appData
     */
    @Update
    fun updateAppData(appData: AppData)


    /**
     * delete appData
     */
    @Delete
    fun deleteAppData(appData: AppData)


    /**
     * select appData by app id
     */
    @Query("SELECT * FROM AppData WHERE id = :id")
    fun selectAppDataById(id: String): AppData


    /**
     * select appData by app id
     * @return liveData<AppData>
     */
    @Query("SELECT * FROM AppData WHERE id = :id")
    fun selectAppDataLiveDataById(id: String): LiveData<AppData>
}