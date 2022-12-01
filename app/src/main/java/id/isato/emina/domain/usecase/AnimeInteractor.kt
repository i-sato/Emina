package id.isato.emina.domain.usecase

import id.isato.emina.data.Resource
import id.isato.emina.domain.model.AnimeDomain
import id.isato.emina.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class AnimeInteractor @Inject constructor(
    private val repository: AnimeRepository
): AnimeUseCase {

    override suspend fun getTopAnime(): Flow<Resource<List<AnimeDomain>>> =
        repository.getTopAnime().distinctUntilChanged()

}