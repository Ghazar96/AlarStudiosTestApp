package com.example.alarstudiostestapp.network.entitys

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Place(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
) : Serializable
