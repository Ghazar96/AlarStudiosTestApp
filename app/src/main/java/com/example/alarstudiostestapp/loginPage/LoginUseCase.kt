package com.example.alarstudiostestapp.loginPage

import com.example.alarstudiostestapp.UserCodeSaveService
import com.example.alarstudiostestapp.UserCodeSaveServiceImpl
import com.example.alarstudiostestapp.network.repos.LoginNetworkRepository
import com.example.alarstudiostestapp.network.services.LoginState

class LoginUseCase(val loginNetworkRepository: LoginNetworkRepository) {
    suspend fun login(username: String, password: String): LoginState {
        return loginNetworkRepository.login(username, password)
    }
}
