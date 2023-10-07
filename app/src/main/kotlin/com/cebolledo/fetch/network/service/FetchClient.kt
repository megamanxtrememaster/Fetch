package com.cebolledo.fetch.network.service

import com.cebolledo.fetch.network.model.FetchResponse
import retrofit2.Call
import javax.inject.Inject

class FetchClient @Inject constructor(private val fetchService: FetchService)
{
    suspend fun fetchData(): List<FetchResponse> = fetchService.fetchData()
}