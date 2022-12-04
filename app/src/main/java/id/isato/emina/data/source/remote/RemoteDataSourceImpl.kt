package id.isato.emina.data.source.remote

import id.isato.emina.data.source.remote.network.ApiResponse
import id.isato.emina.data.source.remote.network.ApiService
import id.isato.emina.data.source.remote.response.AnimeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): RemoteDataSource {

    override suspend fun getTopAnime(page: Int): Flow<ApiResponse<List<AnimeResponse>>> {
        return flow {
            val response = apiService.getTopAnime()
            val anime = response.data
            when {
                anime.isNotEmpty() -> emit(ApiResponse.Success(anime))
                else -> emit(ApiResponse.Empty)
            }
        }.catch {
            emit(ApiResponse.Error(it.toString()))
        }
    }

}