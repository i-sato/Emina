package id.isato.emina.utils

import id.isato.emina.data.source.remote.response.AnimeResponse
import id.isato.emina.domain.model.AnimeDomain
import id.isato.emina.domain.model.ProfileDomain
import id.isato.emina.ui.model.Anime
import id.isato.emina.ui.model.Profile

fun ProfileDomain.asPresentation(): Profile {
    return Profile(
        userName = userName,
        occupation = occupation,
        avatar = avatar,
        bio = bio,
        backdrop = backdrop
    )
}

fun AnimeResponse.asDomain(): AnimeDomain {
    return AnimeDomain(
        malId = malId,
        title = title,
        imageUrl = images.webp.imageUrl
    )
}

fun AnimeDomain.asPresentation(): Anime {
    return Anime(
        malId = malId,
        title = title,
        imageUrl = imageUrl
    )
}