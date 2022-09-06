package com.szn.movies.domain.model

import android.os.Parcelable
import com.szn.movies.domain.BuildConfig
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    val id: Int,
    val title: String,
    val adult: Boolean = false,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    var original_language: String?,
    val original_title: String?,
    var overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    var release_date: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int
) : Parcelable {
    constructor(id: Int, title: String, poster_path: String?, back_path: String?): this(id, title, false, back_path,null, null, null, null, null, poster_path, null, null, null, 0)

    // Used by fake movie
    constructor(id: Int, title: String, desc: String?, poster_path: String?, back_path: String?, released: String?): this(id, title, false, back_path,null, "FR", null, desc, null, poster_path, released, null, null, 0)

    fun getImage(): String? {
        return BuildConfig.IMAGE_BASE + poster_path
    }

    fun getBackImage(): String? {
        return BuildConfig.IMAGE_BASE + backdrop_path
    }
}

val fakeMovie = Video(0, "Les minions, il était une fois GRU", "Alors que les années 70 battent leur plein, Gru qui grandit en banlieue au milieu des jeans à pattes d’éléphants et des chevelures en fleur, met sur pied un plan machiavélique à souhait pour réussir à intégrer un groupe célèbre de super méchants, connu sous le nom de Vicious 6, dont il est le plus grand fan. Il est secondé dans sa tâche par les Minions, ses petits compagnons aussi turbulents que fidèles. Avec l’aide de Kevin, Stuart, Bob et Otto – un nouveau Minion arborant un magnifique appareil dentaire et un besoin désespéré de plaire - ils vont déployer ensemble des trésors d’ingéniosité afin de construire leur premier repaire, expérimenter leurs premières armes, et lancer leur première mission. Lorsque les Vicious 6 limogent leur chef, le légendaire \" Wild Knuckles \", Gru passe l’audition pour intégrer l’équipe. Le moins qu’on puisse dire c’est que l’entrevue tourne mal, et soudain court quand Gru leur démontre sa supériorité et se retrouve soudain leur ennemi juré.",
    "https://image.tmdb.org/t/p/w500/qsGrZgwOs8B6Jqen0ECBJ6UgfEG.jpg", "https://image.tmdb.org/t/p/w500/nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg", "2022-06-11")
