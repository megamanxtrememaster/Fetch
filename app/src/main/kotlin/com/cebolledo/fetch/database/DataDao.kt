package com.cebolledo.fetch.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cebolledo.fetch.database.entity.DataEntity

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(vararg data:DataEntity)

    @Query("SELECT * FROM DataEntity WHERE id = :id")
    suspend fun getData(id: Int): List<DataEntity>

    @Query("SELECT * FROM DataEntity WHERE name is NOT NULL AND LENGTH(name) > 0 ORDER BY listId ASC, name ASC")
    suspend fun getData():List<DataEntity>

    @Delete
    suspend fun deleteData(vararg data: DataEntity): Unit

}
