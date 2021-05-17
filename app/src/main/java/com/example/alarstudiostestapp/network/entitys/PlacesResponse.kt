package com.example.alarstudiostestapp.network.entitys

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlacesResponse(
    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("data")
    @Expose
    val data: List<Place>
)
