package id.isato.emina.data

import id.isato.emina.data.source.remote.response.*

object DummyData {

    private val paginationItemResponse = PaginationItemResponse(
        count = 25,
        total = 21574,
        perPage = 25
    )

    private val animeFma= AnimeResponse(
        malId = 5114,
        url = "https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood",
        images = ImageResponse(
            JpgResponse(
                imageUrl = "https://cdn.myanimelist.net/images/anime/1208/94745.jpg",
                smallImageUrl = "https://cdn.myanimelist.net/images/anime/1208/94745t.jpg",
                largeImageUrl = "https://cdn.myanimelist.net/images/anime/1208/94745l.jpg"
            ),
            WebpResponse(
                imageUrl = "https://cdn.myanimelist.net/images/anime/1208/94745.webp",
                smallImageUrl = "https://cdn.myanimelist.net/images/anime/1208/94745t.webp",
                largeImageUrl = "https://cdn.myanimelist.net/images/anime/1208/94745l.webp"
            )
        ),
        trailer = TrailerResponse(
            youtubeId = "--IcmZkvL0Q",
            url = "https://www.youtube.com/watch?v=--IcmZkvL0Q",
            embedUrl = "https://www.youtube.com/embed/--IcmZkvL0Q?enablejsapi=1&wmode=opaque&autoplay=1"
        ),
        title = "Fullmetal Alchemist: Brotherhood",
        episodes = 64,
        status = "Finished Airing",
        score = 9.11,
        year = 2009,
        synopsis = "After a horrific alchemy experiment goes wrong in the Elric household, brothers Edward and Alphonse are left in a catastrophic new reality. Ignoring the alchemical principle banning human transmutation, the boys attempted to bring their recently deceased mother back to life. Instead, they suffered brutal personal loss: Alphonse's body disintegrated while Edward lost a leg and then sacrificed an arm to keep Alphonse's soul in the physical realm by binding it to a hulking suit of armor.\\n\\nThe brothers are rescued by their neighbor Pinako Rockbell and her granddaughter Winry. Known as a bio-mechanical engineering prodigy, Winry creates prosthetic limbs for Edward by utilizing \\\"automail,\\\" a tough, versatile metal used in robots and combat armor. After years of training, the Elric brothers set off on a quest to restore their bodies by locating the Philosopher's Stone—a powerful gem that allows an alchemist to defy the traditional laws of Equivalent Exchange.\\n\\nAs Edward becomes an infamous alchemist and gains the nickname \\\"Fullmetal,\\\" the boys' journey embroils them in a growing conspiracy that threatens the fate of the world.\\n\\n[Written by MAL Rewrite]",
        genres = listOf(
            GenreResponse(malId = 1, type = "anime", name = "Action", url = "https://myanimelist.net/anime/genre/1/Action"),
            GenreResponse(malId = 2, type = "anime", name = "Adventure", url = "https://myanimelist.net/anime/genre/2/Adventure"),
            GenreResponse(malId = 8, type = "anime", name = "Drama", url = "https://myanimelist.net/anime/genre/8/Drama"),
            GenreResponse(malId = 10, type = "anime", name = "Fantasy", url = "https://myanimelist.net/anime/genre/10/Fantasy")
        )
    )

    private val animeBleach= AnimeResponse(
        malId = 41467,
        url = "https://myanimelist.net/anime/41467/Bleach__Sennen_Kessen-hen",
        images = ImageResponse(
            JpgResponse(
                imageUrl = "https://cdn.myanimelist.net/images/anime/1764/126627.jpg",
                smallImageUrl = "https://cdn.myanimelist.net/images/anime/1764/126627t.jpg",
                largeImageUrl = "https://cdn.myanimelist.net/images/anime/1764/126627l.jpg"
            ),
            WebpResponse(
                imageUrl = "https://cdn.myanimelist.net/images/anime/1764/126627.webp",
                smallImageUrl = "https://cdn.myanimelist.net/images/anime/1764/126627t.webp",
                largeImageUrl = "https://cdn.myanimelist.net/images/anime/1764/126627l.webp"
            )
        ),
        trailer = TrailerResponse(
            youtubeId = "e8YBesRKq_U",
            url = "https://www.youtube.com/watch?v=e8YBesRKq_U",
            embedUrl = "https://www.youtube.com/embed/e8YBesRKq_U?enablejsapi=1&wmode=opaque&autoplay=1"
        ),
        title = "Bleach: Sennen Kessen-hen",
        episodes = 13,
        status = "Currently Airing",
        score = 9.1,
        year = 2022,
        synopsis = "Substitute Soul Reaper Ichigo Kurosaki spends his days fighting against Hollows, dangerous evil spirits that threaten Karakura Town. Ichigo carries out his quest with his closest allies: Orihime Inoue, his childhood friend with a talent for healing; Yasutora Sado, his high school classmate with superhuman strength; and Uryuu Ishida, Ichigo's Quincy rival.\\n\\nIchigo's vigilante routine is disrupted by the sudden appearance of Asguiaro Ebern, a dangerous Arrancar who heralds the return of Yhwach, an ancient Quincy king. Yhwach seeks to reignite the historic blood feud between Soul Reaper and Quincy, and he sets his sights on erasing both the human world and the Soul Society for good.\\n\\nYhwach launches a two-pronged invasion into both the Soul Society and Hueco Mundo, the home of Hollows and Arrancar. In retaliation, Ichigo and his friends must fight alongside old allies and enemies alike to end Yhwach's campaign of carnage before the world itself comes to an end.\\n\\n[Written by MAL Rewrite]",
        genres = listOf(
            GenreResponse(malId = 1, type = "anime", name = "Action", url = "https://myanimelist.net/anime/genre/1/Action"),
            GenreResponse(malId = 2, type = "anime", name = "Adventure", url = "https://myanimelist.net/anime/genre/2/Adventure"),
            GenreResponse(malId = 10, type = "anime", name = "Fantasy", url = "https://myanimelist.net/anime/genre/10/Fantasy")
        )
    )

    @Suppress("Unused")
    fun generatePaginationResponse(): PaginationResponse {
        return PaginationResponse(
            lastVisiblePage = 863,
            hasNextPage = true,
            currentPage = 1,
            items = paginationItemResponse,
            data = generateAnimeResponses()
        )
    }

    fun generateAnimeResponses(): List<AnimeResponse> {
        return listOf(
            animeFma,
            animeBleach
        )
    }

}