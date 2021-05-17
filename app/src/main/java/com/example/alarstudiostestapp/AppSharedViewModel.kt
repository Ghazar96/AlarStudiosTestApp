package com.example.alarstudiostestapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppSharedViewModel(val codeSaveService: UserCodeSaveService) : ViewModel() {

    private var _openMainPageLiveData: MutableLiveData<String> = MutableLiveData()
    var openMainPageLiveData: LiveData<String> = _openMainPageLiveData

    private var _openLoginPageLiveData: MutableLiveData<Unit> = MutableLiveData()
    var openLoginPageLiveData: LiveData<Unit> = _openLoginPageLiveData

    init {
        codeSaveService.getCode()?.let {
            openMainPage(it)
        } ?: openLoginPage()
    }

    private fun openLoginPage() {
        _openLoginPageLiveData.value = Unit
    }

    fun openMainPage(code: String) {
        _openMainPageLiveData.value = code
    }
}