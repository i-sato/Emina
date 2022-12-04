package id.isato.emina.data.repository

import id.isato.emina.data.Resource
import id.isato.emina.data.source.NetworkBoundResource
import id.isato.emina.data.source.local.LocalDataSource
import id.isato.emina.data.source.remote.RemoteDataSource
import id.isato.emina.data.source.remote.network.ApiResponse
import id.isato.emina.data.source.remote.response.AnimeResponse
import id.isato.emina.di.IoDispatcher
import id.isato.emina.domain.model.AnimeDetailDomain
import id.isato.emina.domain.model.AnimeDomain
import id.isato.emina.domain.repository.AnimeRepository
import id.isato.emina.utils.asDetailDomain
import id.isato.emina.utils.asDomain
import id.isato.emina.utils.asEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): AnimeRepository {

    override fun getTopAnime(): Flow<Resource<List<AnimeDomain>>> =
        object: NetworkBoundResource<List<AnimeDomain>, List<AnimeResponse>>() {
            override fun loadFromDB(): Flow<List<AnimeDomain>> =
                local.getTopAnime().map { anime ->
                    anime.map { it.asDomain() }
                }.flowOn(ioDispatcher)

            override fun shouldFetch(data: List<AnimeDomain>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<AnimeResponse>>> =
                remote.getTopAnime(1)

            override suspend fun saveCallResult(data: List<AnimeResponse>) {
                val anime = data.map { it.asEntity() }
                local.insertAnime(anime)
            }
        }.asFlow().flowOn(ioDispatcher)

    override suspend fun getAnimeById(malId: Int): AnimeDetailDomain =
        local.getAnimeById(malId).asDetailDomain()

}