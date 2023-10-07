package com.cebolledo.fetch.network.service

import com.cebolledo.fetch.network.model.FetchResponse
import retrofit2.Call
import retrofit2.http.GET

interface FetchService {

    @GET("hiring.json")
    suspend fun fetchData(): List<FetchResponse>
}