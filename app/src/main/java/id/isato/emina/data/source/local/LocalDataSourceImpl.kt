package id.isato.emina.data.source.local

import id.isato.emina.data.source.local.entity.AnimeEntity
import id.isato.emina.data.source.local.room.AnimeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val animeDao: AnimeDao
): LocalDataSource {

    override fun getTopAnime(): Flow<List<AnimeEntity>> =
        animeDao.getDistinctTopAnime()

    override suspend fun insertAnime(anime: List<AnimeEntity>) =
        animeDao.insertAnime(anime)

    override suspend fun getAnimeById(malId: Int): AnimeEntity =
        animeDao.getAnimeById(malId)
}