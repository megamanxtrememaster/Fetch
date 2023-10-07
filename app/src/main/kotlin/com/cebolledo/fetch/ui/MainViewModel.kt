package com.cebolledo.fetch.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cebolledo.fetch.database.entity.DataEntity
import com.cebolledo.fetch.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo:Repository):ViewModel() {

    private var loading = mutableStateOf(false)
    private var errorMessage   = mutableStateOf<String?>(null)

    fun isLoading(): MutableState<Boolean>
    {
        return loading
    }

    fun errorMessage(): MutableState<String?> {
        return errorMessage
    }

    fun getData(): Flow<List<DataEntity>> {
        return repo.fetchData(onStart = {loading.value = true},
            onComplete = {loading.value = false},
            onError = {errorMsg -> errorMessage.value = errorMsg})
    }
}


