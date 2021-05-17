package com.example.alarstudiostestapp.network.entitys

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("code")
    @Expose
    val code: String
)
