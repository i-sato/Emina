package id.isato.emina.data.source.remote

import id.isato.emina.data.source.remote.response.AnimeResponse

interface RemoteDataSource {

    suspend fun getTopAnime(page: Int): List<AnimeResponse>

}