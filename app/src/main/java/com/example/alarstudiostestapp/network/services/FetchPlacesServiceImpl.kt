package com.example.alarstudiostestapp.network.services

import com.example.alarstudiostestapp.PlacesData
import com.example.alarstudiostestapp.network.mapper.NetworkMapper

class FetchPlacesServiceImpl(
    private val networkServiceApi: NetworkServiceApi,
    private val mapper: NetworkMapper
) : FetchPlacesService {
    override suspend fun getPlaces(code: String, page: Int): PlacesState {
        val places = networkServiceApi.getPlaces(code, page)
        return mapper.fetchPlacesResponseMapper.map(places)
    }
}

sealed class PlacesState {
    data class Success(val items: List<PlacesData>, val page: Int) : PlacesState()
    data class Error(val message: String) : PlacesState()
}
