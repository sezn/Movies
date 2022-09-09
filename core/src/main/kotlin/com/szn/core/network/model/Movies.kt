package com.szn.core.network.model

import com.szn.core.network.model.user.Movie

data class Movies(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)