package com.cebolledo.fetch.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.cebolledo.fetch.model.DataModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class TypeResponseConverter @Inject constructor(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<DataModel>? {
        val listType =
            Types.newParameterizedType(List::class.java, DataModel::class.java)
        val adapter: JsonAdapter<List<DataModel>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromInfoType(type: List<DataModel>?): String {
        val listType =
            Types.newParameterizedType(List::class.java, DataModel::class.java)
        val adapter: JsonAdapter<List<DataModel>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}
