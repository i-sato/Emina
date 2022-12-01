package id.isato.emina.data.repository

import id.isato.emina.data.Resource
import id.isato.emina.data.source.remote.RemoteDataSource
import id.isato.emina.di.IoDispatcher
import id.isato.emina.domain.model.AnimeDomain
import id.isato.emina.domain.repository.AnimeRepository
import id.isato.emina.utils.asDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): AnimeRepository {

    override fun getTopAnime(): Flow<Resource<List<AnimeDomain>>> = flow {
        try {
            val response = remote.getTopAnime(1)
            val anime = response.map { it.asDomain() }
            emit(Resource.Success(anime))
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }.flowOn(ioDispatcher)
}