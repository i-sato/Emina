package id.isato.emina.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TrailerResponse(

    @field:SerializedName("youtube_id")
    val youtubeId: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("embed_url")
    val embedUrl: String? = null,

)
