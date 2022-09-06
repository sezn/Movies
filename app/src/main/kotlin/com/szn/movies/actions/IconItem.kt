package com.szn.movies.actions

import com.szn.movies.R

data class IconItem(val name: String,
                    val text: String?,
                    val resId: Int,
                    val invertResId: Int)

val movieActions = mutableListOf(
    IconItem("Regarder", "Regarder", R.drawable.ic_play_arrow, R.drawable.ic_play_arrow),
    IconItem("Favori", null, R.drawable.ic_star_outline_24, R.drawable.ic_baseline_star_24)
)