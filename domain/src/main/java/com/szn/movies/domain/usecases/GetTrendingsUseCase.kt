package com.szn.movies.domain.usecases

import com.szn.movies.domain.MoviesRepository
import com.szn.movies.domain.model.Video
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetTrendingsUseCase(private val moviesRepository: MoviesRepository,
                          private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default) {

    suspend operator fun invoke(): List<Video> = withContext(defaultDispatcher) {
        val movies = moviesRepository.getTrendings()
        val result: MutableList<Video> = mutableListOf()
        // This is not parallelized, the use case is linearly slow.
      /*  for (article in news) {
            // The repository exposes suspend functions
            val author = authorsRepository.getAuthor(article.authorId)
            result.add(ArticleWithAuthor(article, author))
        }*/
        result
    }
}