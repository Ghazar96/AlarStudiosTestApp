package com.example.alarstudiostestapp.network.repos

import com.example.alarstudiostestapp.network.services.PlacesState

interface FetchPlacesRepo {
    suspend fun getPlaces(code: String, page: Int): PlacesState
}