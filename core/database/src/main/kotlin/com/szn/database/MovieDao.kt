package com.szn.database

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE id IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE title LIKE :first AND " +
           " :last LIMIT 1")
    suspend fun findByName(first: String, last: String): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(Movie: MovieEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(Movies: List<MovieEntity>): LongArray

    @Delete
    suspend fun delete(Movie: MovieEntity)

    @Query("DELETE FROM movies")
    suspend fun clear()

    @Query("SELECT * FROM movies")
    fun pagingSource(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun pagingPopularSource(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies ORDER BY (vote_average * vote_count) DESC")
    fun pagingTopRatedSource(): PagingSource<Int, MovieEntity>

}


