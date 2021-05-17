package com.example.alarstudiostestapp.network.repos

import com.example.alarstudiostestapp.network.services.LoginNetworkService
import com.example.alarstudiostestapp.network.services.LoginState

class LoginNetworkRepositoryImpl(val service: LoginNetworkService) : LoginNetworkRepository {
    override suspend fun login(username: String, password: String): LoginState {
        return service.login(username, password)
    }
}
