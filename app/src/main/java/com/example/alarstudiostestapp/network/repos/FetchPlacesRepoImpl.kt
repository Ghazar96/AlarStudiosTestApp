package com.example.alarstudiostestapp.network.repos

import com.example.alarstudiostestapp.network.services.FetchPlacesService
import com.example.alarstudiostestapp.network.services.PlacesState

class FetchPlacesRepoImpl(private val fetchPlacesService: FetchPlacesService) : FetchPlacesRepo {
    override suspend fun getPlaces(code: String, page: Int): PlacesState {
        return fetchPlacesService.getPlaces(code, page)
    }
}