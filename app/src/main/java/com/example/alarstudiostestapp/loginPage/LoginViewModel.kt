package com.example.alarstudiostestapp.loginPage

import androidx.lifecycle.*
import com.example.alarstudiostestapp.UserCodeSaveService
import com.example.alarstudiostestapp.network.services.LoginState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    val savedStateHandle: SavedStateHandle,
    val loginUseCase: LoginUseCase,
    private val codeSaveService: UserCodeSaveService
) :
    ViewModel() {
    private var _showErrorLiveData: MutableLiveData<String> = MutableLiveData()
    var showErrorLiveData: LiveData<String> = _showErrorLiveData

    private var _loginCompleteLiveData: MutableLiveData<Unit> = MutableLiveData()
    var loginCompleteLiveData: LiveData<Unit> = _loginCompleteLiveData

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val loginState = loginUseCase.login(username, password)

            if (loginState is LoginState.Success) {
                _loginCompleteLiveData.postValue(Unit)
                codeSaveService.saveCode(loginState.code)
            } else if (loginState is LoginState.Error) {
                _showErrorLiveData.postValue(loginState.message)
            }
        }
    }
}
