package id.isato.emina.data.source.remote.network

import id.isato.emina.data.source.remote.response.PaginationResponse
import retrofit2.http.GET

interface ApiService {

    @GET("top/anime")
    suspend fun getTopAnime(): PaginationResponse

}