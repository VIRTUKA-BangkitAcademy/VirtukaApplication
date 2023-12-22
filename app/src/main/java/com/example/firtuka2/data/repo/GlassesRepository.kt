package com.example.firtuka2.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.firtuka2.data.PagingSource
import com.example.firtuka2.data.response.FramesItem
import com.example.firtuka2.data.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class GlassesRepository( private  val apiService: ApiService) {

    fun getPagingGlasses(): Flow<PagingData<FramesItem>> {
        val pagingSourceFactory = { PagingSource(apiService) }
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}