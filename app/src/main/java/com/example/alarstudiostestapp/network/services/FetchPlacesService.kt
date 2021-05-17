package com.example.alarstudiostestapp.network.services

interface FetchPlacesService {
    suspend fun getPlaces(code: String, page: Int): PlacesState
}