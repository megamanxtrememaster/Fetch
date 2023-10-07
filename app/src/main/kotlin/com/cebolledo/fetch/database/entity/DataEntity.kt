package com.cebolledo.fetch.database.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cebolledo.fetch.model.DataModel
import com.cebolledo.fetch.network.model.FetchResponse

@Entity
data class DataEntity(
    @PrimaryKey val id:Int,
    val listId:Int,
    val name:String?
){
    fun toModel(): DataModel
    {
        return DataModel(this.id, this.listId, this.name)
    }

    fun toResponse():FetchResponse
    {
        return FetchResponse(this.id, this.listId, this.name)
    }
}
