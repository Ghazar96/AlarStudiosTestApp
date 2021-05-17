package com.example.alarstudiostestapp.di

import android.content.SharedPreferences
import androidx.lifecycle.SavedStateHandle
import com.example.alarstudiostestapp.AppSharedViewModel
import com.example.alarstudiostestapp.UserCodeSaveService
import com.example.alarstudiostestapp.UserCodeSaveServiceImpl
import com.example.alarstudiostestapp.loginPage.LoginUseCase
import com.example.alarstudiostestapp.loginPage.LoginViewModel
import com.example.alarstudiostestapp.mainPage.GetPlacesUseCase
import com.example.alarstudiostestapp.mainPage.MainFragmentViewModel
import com.example.alarstudiostestapp.network.mapper.NetworkMapper
import com.example.alarstudiostestapp.network.repos.FetchPlacesRepo
import com.example.alarstudiostestapp.network.repos.FetchPlacesRepoImpl
import com.example.alarstudiostestapp.network.repos.LoginNetworkRepository
import com.example.alarstudiostestapp.network.repos.LoginNetworkRepositoryImpl
import com.example.alarstudiostestapp.network.services.*
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun appModule(sharedPreferences: SharedPreferences) = module {
    factory<LoginNetworkService> {
        LoginNetworkServiceImpl(
            Retrofit.Builder()
                .callFactory(OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.alarstudios.com/")
                .build().create(NetworkServiceApi::class.java),
            mapper = NetworkMapper()
        )
    }

    factory<LoginNetworkRepository> { LoginNetworkRepositoryImpl(service = get()) }

    factory<LoginUseCase> {
        LoginUseCase(loginNetworkRepository = get())
    }

    viewModel { (handle: SavedStateHandle) ->
        LoginViewModel(
            savedStateHandle = handle,
            loginUseCase = get(),
            codeSaveService = get()
        )
    }

    factory<FetchPlacesService> {
        FetchPlacesServiceImpl(
            Retrofit.Builder()
                .callFactory(OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.alarstudios.com/")
                .build().create(NetworkServiceApi::class.java),
            mapper = NetworkMapper()
        )
    }

    factory<FetchPlacesRepo> { FetchPlacesRepoImpl(fetchPlacesService = get()) }

    factory<UserCodeSaveService> { UserCodeSaveServiceImpl(sharedPreferences) }

    factory<GetPlacesUseCase> { GetPlacesUseCase(fetchPlacesRepo = get(), codeSaveService = get()) }

    viewModel { (handle: SavedStateHandle) ->
        MainFragmentViewModel(
            savedStateHandle = handle,
            getPlacesUseCase = get()
        )
    }

    viewModel {
        AppSharedViewModel(codeSaveService = get())
    }
}