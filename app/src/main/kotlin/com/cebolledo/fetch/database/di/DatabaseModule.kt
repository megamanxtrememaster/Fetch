package com.cebolledo.fetch.database.di


import android.app.Application
import androidx.room.Room
import com.cebolledo.fetch.database.DataDao
import com.cebolledo.fetch.database.DataDatabase
import com.cebolledo.fetch.database.TypeResponseConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
        //typeResponseConverter: TypeResponseConverter
    ): DataDatabase {
        return Room
            .databaseBuilder(application, DataDatabase::class.java, "fetch.db")
            .fallbackToDestructiveMigration()
            //.addTypeConverter(typeResponseConverter)
            .build()
    }

    @Provides
    @Singleton
    fun provideDataDao(appDatabase: DataDatabase): DataDao {
        return appDatabase.dataDao()
    }

    @Provides
    @Singleton
    fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter {
        return TypeResponseConverter(moshi)
    }
}
