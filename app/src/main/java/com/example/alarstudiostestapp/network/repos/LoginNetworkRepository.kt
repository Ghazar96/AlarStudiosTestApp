package com.example.alarstudiostestapp.network.repos

import com.example.alarstudiostestapp.network.services.LoginState

interface LoginNetworkRepository {
    suspend fun login(username: String, password: String) : LoginState
}