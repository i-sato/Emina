package id.isato.emina.data.source.local

import id.isato.emina.data.source.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getTopAnime(): Flow<List<AnimeEntity>>

    suspend fun insertAnime(anime: List<AnimeEntity>)

    suspend fun getAnimeById(malId: Int): AnimeEntity

}