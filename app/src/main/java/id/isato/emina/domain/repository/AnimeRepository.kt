package id.isato.emina.domain.repository

import id.isato.emina.data.Resource
import id.isato.emina.domain.model.AnimeDetailDomain
import id.isato.emina.domain.model.AnimeDomain
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    fun getTopAnime(): Flow<Resource<List<AnimeDomain>>>

    fun getFavoriteAnime(): Flow<List<AnimeDomain>>

    suspend fun getAnimeById(malId: Int): AnimeDetailDomain

    suspend fun updateFavorite(malId: Int, isFavorite: Boolean)

}