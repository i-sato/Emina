package id.isato.emina.data.source.local

import id.isato.emina.data.source.local.entity.AnimeEntity
import id.isato.emina.data.source.local.room.AnimeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val animeDao: AnimeDao
): LocalDataSource {

    override fun getTopAnime(animeTitle: String?): Flow<List<AnimeEntity>> {
        return when {
            animeTitle.isNullOrBlank() -> animeDao.getDistinctTopAnime()
            else -> animeDao.getDistinctFilteredTopAnime(animeTitle)
        }
    }

    override fun getFavoriteAnime(): Flow<List<AnimeEntity>> =
        animeDao.getDistinctFavoriteAnime()

    override suspend fun insertAnime(anime: List<AnimeEntity>) =
        animeDao.insertAnime(anime)

    override suspend fun getAnimeById(malId: Int): AnimeEntity =
        animeDao.getAnimeById(malId)

    override suspend fun updateFavorite(malId: Int, isFavorite: Boolean) =
        animeDao.updateFavorite(malId, isFavorite)
}