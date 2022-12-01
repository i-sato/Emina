package id.isato.emina.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AnimeResponse(

    @field:SerializedName("mal_id")
    val malId: Int,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("images")
    val images: ImageResponse,

    @field:SerializedName("trailer")
    val trailer: TrailerResponse? = null,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("episodes")
    val episodes: Int? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("score")
    val score: Double? = null,

    @field:SerializedName("year")
    val year: Int? = null,

    @field:SerializedName("synopsis")
    val synopsis: String? = null,

    @field:SerializedName("genres")
    val genres: List<GenreResponse?>? = null,

)

