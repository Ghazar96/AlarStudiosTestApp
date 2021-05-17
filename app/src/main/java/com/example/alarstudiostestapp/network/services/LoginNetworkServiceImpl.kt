package com.example.alarstudiostestapp.network.services

import com.example.alarstudiostestapp.network.mapper.NetworkMapper

class LoginNetworkServiceImpl(
    private val networkServiceApi: NetworkServiceApi,
    private val mapper: NetworkMapper
) : LoginNetworkService {
    override suspend fun login(username: String, password: String): LoginState {
        val loginResponse = networkServiceApi.login(username, password)
        return mapper.loginResponseMapper.map(loginResponse)
    }
}

sealed class LoginState {
    data class Success(val code: String) : LoginState()
    data class Error(val message: String) : LoginState()
}