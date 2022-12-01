package id.isato.emina.ui.model

data class Anime(
    val malId: Int,
    val title: String,
    val imageUrl: String
) {
    companion object {
        fun mock(): Anime = Anime(
            malId = 41467,
            title = "Bleach: Sennen Kessen-hen",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1764/126627.webp"
        )
    }
}
