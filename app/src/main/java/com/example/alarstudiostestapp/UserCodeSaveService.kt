package com.example.alarstudiostestapp

interface UserCodeSaveService {
    fun saveCode(code: String)

    fun getCode(): String?
}