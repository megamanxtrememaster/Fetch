package com.cebolledo.fetch.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cebolledo.fetch.database.entity.DataEntity

@Database(
    entities = [DataEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(TypeResponseConverter::class)
abstract class DataDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao
}
