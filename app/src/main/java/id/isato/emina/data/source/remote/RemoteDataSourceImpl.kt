package id.isato.emina.data.source.remote

import id.isato.emina.data.source.remote.network.ApiService
import id.isato.emina.data.source.remote.response.AnimeResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): RemoteDataSource {

    override suspend fun getTopAnime(page: Int): List<AnimeResponse> {
        return try {
            val response = apiService.getTopAnime()
            response.data
        } catch (e: Exception) {
            throw Exception("An error occurred when get anime. $e")
        }
    }
}