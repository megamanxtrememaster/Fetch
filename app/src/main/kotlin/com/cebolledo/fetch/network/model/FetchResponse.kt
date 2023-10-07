package com.cebolledo.fetch.network.model

import com.cebolledo.fetch.database.entity.DataEntity
import com.cebolledo.fetch.model.DataModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class FetchResponse(
   val id: Int,
    val listId: Int,
    val name: String?,
){
    fun toModel(): DataModel
    {
        return DataModel(this.id, this.listId, this.name)
    }

    fun toEntity():DataEntity
    {
        return DataEntity(this.id, this.listId, this.name)
    }
}
