package com.cebolledo.fetch.model

import com.squareup.moshi.JsonClass
import android.os.Parcelable
import com.squareup.moshi.Json

//import kotlinx.parcelize.Parcelize

//@Parcelize
@JsonClass(generateAdapter = true)
data class DataModel (
    @field:Json(name="id") val id:Int,
    @field:Json(name="listId") val listId:Int,
    @field:Json(name="name") val name:String? )