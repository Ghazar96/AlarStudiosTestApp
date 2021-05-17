package com.example.alarstudiostestapp.network.mapper

import com.example.alarstudiostestapp.PlacesData
import com.example.alarstudiostestapp.network.entitys.LoginResponse
import com.example.alarstudiostestapp.network.entitys.Place
import com.example.alarstudiostestapp.network.entitys.PlacesResponse
import com.example.alarstudiostestapp.network.services.LoginState
import com.example.alarstudiostestapp.network.services.PlacesState

class NetworkMapper {
    val loginResponseMapper = object : Mapper<LoginResponse, LoginState> {
        override fun map(s: LoginResponse): LoginState {
            return if (s.status == "ok") {
                loginResponseOkMapper.map(s)
            } else {
                loginResponseErrorMapper.map(s)
            }
        }
    }

    val loginResponseOkMapper = object : Mapper<LoginResponse, LoginState.Success> {
        override fun map(s: LoginResponse): LoginState.Success {
            return LoginState.Success(s.code)
        }
    }

    val loginResponseErrorMapper = object : Mapper<LoginResponse, LoginState.Error> {
        override fun map(s: LoginResponse): LoginState.Error {
            return LoginState.Error("Invalid username or password")
        }
    }

    val fetchPlacesResponseMapper = object : Mapper<PlacesResponse, PlacesState> {
        override fun map(s: PlacesResponse): PlacesState {
            return if (s.status == "ok") {
                fetchPlacesResponseOkMapper.map(s)
            } else {
                fetchPlacesResponseErrorMapper.map(s)
            }
        }
    }

    val fetchPlacesResponseOkMapper = object : Mapper<PlacesResponse, PlacesState.Success> {
        override fun map(s: PlacesResponse): PlacesState.Success {
            return PlacesState.Success(placesMapperToPlaceData.map(s.data), s.page)
        }
    }

    val placesMapperToPlaceData = object : Mapper<Place, PlacesData> {
        override fun map(s: Place): PlacesData {
            return PlacesData(
                id = s.id,
                name = s.name,
                country = s.country,
                lat = s.lat,
                lon = s.lon
            )
        }
    }

    val fetchPlacesResponseErrorMapper = object : Mapper<PlacesResponse, PlacesState.Error> {
        override fun map(s: PlacesResponse): PlacesState.Error {
            return PlacesState.Error("Something went wrong")
        }
    }
}