package id.isato.emina.domain.model

data class AnimeDetailDomain(
    val malId: Int,
    val title: String,
    val imageUrl: String,
    val synopsis: String?,
    val isFavorite: Boolean
)
