package com.example.alarstudiostestapp.mainPage

import androidx.lifecycle.*
import com.example.alarstudiostestapp.PlacesData
import com.example.alarstudiostestapp.network.services.PlacesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    val savedStateHandle: SavedStateHandle,
    private val getPlacesUseCase: GetPlacesUseCase
) : ViewModel() {

    private var page = 0

    private var _showErrorLiveData: MutableLiveData<String> = MutableLiveData()
    var showErrorLiveData: LiveData<String> = _showErrorLiveData

    private var _dataCompleteLiveData: MutableLiveData<List<PlacesData>> = MutableLiveData()
    var dataCompleteLiveData: LiveData<List<PlacesData>> = _dataCompleteLiveData

    fun getNextPlaces() {
        viewModelScope.launch(Dispatchers.IO) {
            val state = getPlacesUseCase.getPlaces(page)

            if (state is PlacesState.Success) {
                ++page
                _dataCompleteLiveData.postValue(state.items)
            } else if (state is PlacesState.Error) {
                _showErrorLiveData.postValue(state.message)
            }
        }
    }
}