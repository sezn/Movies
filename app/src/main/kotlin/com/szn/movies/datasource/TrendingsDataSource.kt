package com.szn.movies.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.szn.core.repos.MoviesRepo
import com.szn.movies.domain.model.Video

class TrendingsDataSource(private val moviesRepository: MoviesRepo): PagingSource<Int, Video>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Video> {
        return try {
            val nextPage = params.key ?: 1
            val playlist = moviesRepository.getTrendings(nextPage)
            playlist.page = nextPage

            LoadResult.Page(
                data = playlist.movies,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = playlist.page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Video>): Int? {
        TODO("Not yet implemented")
    }

}