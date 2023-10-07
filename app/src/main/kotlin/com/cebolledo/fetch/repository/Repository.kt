package com.cebolledo.fetch.repository

import androidx.annotation.WorkerThread
import com.cebolledo.fetch.database.entity.DataEntity
import com.cebolledo.fetch.model.DataModel
import kotlinx.coroutines.flow.Flow


/**
 * Repo Interface that will fetch the data
 */


interface Repository
{
    @WorkerThread
    fun fetchData(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ):Flow<List<DataEntity>>


}