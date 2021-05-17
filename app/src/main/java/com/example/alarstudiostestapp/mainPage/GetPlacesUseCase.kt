package com.example.alarstudiostestapp.mainPage

import com.example.alarstudiostestapp.UserCodeSaveService
import com.example.alarstudiostestapp.network.repos.FetchPlacesRepo
import com.example.alarstudiostestapp.network.services.PlacesState

class GetPlacesUseCase(
    private val fetchPlacesRepo: FetchPlacesRepo,
    private val codeSaveService: UserCodeSaveService
) {
    suspend fun getPlaces(page: Int): PlacesState {
        return codeSaveService.getCode()?.let {
            fetchPlacesRepo.getPlaces(it, page)
        } ?: PlacesState.Error("There is no code")

    }
}