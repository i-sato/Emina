package id.isato.emina.domain.repository

import id.isato.emina.data.Resource
import id.isato.emina.domain.model.AnimeDetailDomain
import id.isato.emina.domain.model.AnimeDomain
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    fun getTopAnime(): Flow<Resource<List<AnimeDomain>>>

    suspend fun getAnimeById(malId: Int): AnimeDetailDomain

}