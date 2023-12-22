package com.example.firtuka2.data.repo

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)
    @Query("SELECT * FROM remote_keys WHERE id = :id")
    suspend fun getRemoteKeysId(id: String): RemoteKeys?
    @Query("DELETE FROM remote_keys")
    suspend fun deleteRemoteKeys()
}