package com.example.alarstudiostestapp.network.services

interface LoginNetworkService {
    suspend fun login(username: String, password: String): LoginState
}
