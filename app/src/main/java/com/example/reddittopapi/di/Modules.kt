package com.example.reddittopapi.di

import androidx.room.Room
import com.example.reddittopapi.constants.Constants.BASE_URL
import com.example.reddittopapi.data.AppDatabase
import com.example.reddittopapi.data.network.ApiService
import com.example.reddittopapi.data.repository.ApiRepository
import com.example.reddittopapi.data.repository.DaoRepository
import com.example.reddittopapi.data.repository.Repository
import com.example.reddittopapi.ui.viewModels.PublicationsViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        PublicationsViewModel(get())
    }
}

val netModule = module {
    factory { OkHttpClient.Builder().build() }
    factory { GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create() }
    factory {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
            .create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { ApiRepository(get()) }
    single { DaoRepository(get()) }
    single { Repository(get(), get()) }
}

val dataBaseModule = module {
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "reddit").build() }
    single { get<AppDatabase>().getDao }
}
