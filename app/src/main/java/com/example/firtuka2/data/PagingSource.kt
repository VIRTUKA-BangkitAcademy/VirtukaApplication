package com.example.firtuka2.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.firtuka2.data.response.FramesItem
import com.example.firtuka2.data.retrofit.ApiService

class PagingSource(private val apiService: ApiService) : PagingSource<Int, FramesItem>() {


    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FramesItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getAllFramesPaging(position, params.loadSize)
            val data = responseData.frames
            LoadResult.Page(
                data = data,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (data.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FramesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }



}