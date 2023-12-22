package com.example.firtuka2.data.di

import android.content.Context
import com.example.firtuka2.data.repo.GlassesDatabase
import com.example.firtuka2.data.repo.GlassesRepository
import com.example.firtuka2.data.retrofit.ApiConfig
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepo(context: Context) : GlassesRepository{
        val database = GlassesDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return GlassesRepository(apiService)
    }
}