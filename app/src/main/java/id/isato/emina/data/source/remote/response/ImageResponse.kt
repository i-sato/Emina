@file:Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused"
)

package id.isato.emina.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ImageResponse(

    @field:SerializedName("jpg")
    val jpg: JpgResponse,

    @field:SerializedName("webp")
    val webp: WebpResponse,

)

data class JpgResponse(

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("small_image_url")
    val smallImageUrl: String,

    @field:SerializedName("large_image_url")
    val largeImageUrl: String

)

data class WebpResponse(

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("small_image_url")
    val smallImageUrl: String,

    @field:SerializedName("large_image_url")
    val largeImageUrl: String

)