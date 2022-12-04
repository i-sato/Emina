package id.isato.emina.data.source.remote

import id.isato.emina.data.source.remote.network.ApiResponse
import id.isato.emina.data.source.remote.response.AnimeResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getTopAnime(page: Int): Flow<ApiResponse<List<AnimeResponse>>>

}