package id.isato.emina.domain.usecase

import id.isato.emina.data.Resource
import id.isato.emina.domain.model.AnimeDetailDomain
import id.isato.emina.domain.model.AnimeDomain
import id.isato.emina.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class AnimeInteractor @Inject constructor(
    private val repository: AnimeRepository
): AnimeUseCase {

    override fun getTopAnime(): Flow<Resource<List<AnimeDomain>>> =
        repository.getTopAnime().distinctUntilChanged()

    override fun getFavoriteAnime(): Flow<List<AnimeDomain>> =
        repository.getFavoriteAnime()

    override suspend fun getAnimeById(malId: Int): AnimeDetailDomain =
        repository.getAnimeById(malId)

    override suspend fun updateFavorite(malId: Int, isFavorite: Boolean) =
        repository.updateFavorite(malId, isFavorite)
}