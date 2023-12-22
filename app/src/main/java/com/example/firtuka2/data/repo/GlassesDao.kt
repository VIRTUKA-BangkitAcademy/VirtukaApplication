package com.example.firtuka2.data.repo

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.firtuka2.data.response.FramesItem

interface GlassesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(quote: List<FramesItem>)

    @Query("SELECT * FROM glasses")
    fun getAllGlasses(): PagingSource<Int, FramesItem>

    @Query("DELETE FROM glasses")
    suspend fun deleteAll()
}