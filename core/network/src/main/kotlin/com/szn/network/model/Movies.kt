package com.szn.network.model

@kotlinx.serialization.Serializable
data class Movies(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)