package id.isato.emina.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(

    @field:SerializedName("mal_id")
    val malId: Int,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("url")
    val url: String? = null

)