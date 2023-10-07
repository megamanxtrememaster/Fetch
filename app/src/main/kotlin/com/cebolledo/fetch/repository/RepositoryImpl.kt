package com.cebolledo.fetch.repository


import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import com.cebolledo.fetch.database.DataDao
import com.cebolledo.fetch.database.di.IoDispatcher
import com.cebolledo.fetch.database.entity.DataEntity
import com.cebolledo.fetch.network.service.FetchClient
import com.cebolledo.fetch.network.service.FetchService
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@VisibleForTesting
class RepositoryImpl @Inject constructor(

    private val fetchClient:FetchClient,
    private val dataDao: DataDao,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    @WorkerThread
    override fun fetchData(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<DataEntity>> = flow {

        val data = dataDao.getData()
        if (data.isEmpty())
        {
            try {
                val response = fetchClient.fetchData()
                for (element in response)
                    dataDao.insertData(element.toEntity())
                emit(dataDao.getData())
            }
            catch(e:Exception)
            {
                onError(e.message)
            }
        } else {
            emit(data)
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
