package com.szn.core.db

import androidx.paging.PagingSource
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
    suspend fun insert(Movie: Movie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(Movies: List<Movie>): LongArray

    @Delete
    suspend fun delete(Movie: Movie)

    @Query("DELETE FROM movie")
    suspend fun clear()

    @Query("SELECT * FROM movie")
    fun pagingSource(): PagingSource<Int, Movie>

    @Query("SELECT * FROM movie ORDER BY popularity DESC")
    fun pagingPopularSource(): PagingSource<Int, Movie>

    @Query("SELECT * FROM movie ORDER BY (vote_average * vote_count) DESC")
    fun pagingTopRatedSource(): PagingSource<Int, Movie>

}


