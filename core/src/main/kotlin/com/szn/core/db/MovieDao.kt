package com.szn.core.db

import androidx.room.*
import com.szn.core.network.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE id IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<Movie>

    @Query("SELECT * FROM movie WHERE title LIKE :first AND " +
           " :last LIMIT 1")
    suspend fun findByName(first: String, last: String): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(video: Movie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(videos: List<Movie>): LongArray

    @Delete
    suspend fun delete(video: Movie)
}


