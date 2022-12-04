package id.isato.emina.utils

import id.isato.emina.data.source.local.entity.AnimeEntity
import id.isato.emina.data.source.remote.response.AnimeResponse
import id.isato.emina.domain.model.AnimeDetailDomain
import id.isato.emina.domain.model.AnimeDomain
import id.isato.emina.domain.model.ProfileDomain
import id.isato.emina.ui.model.Anime
import id.isato.emina.ui.model.AnimeDetail
import id.isato.emina.ui.model.Profile

fun ProfileDomain.asPresentation(): Profile {
    return Profile(
        userName = userName,
        email = email,
        occupation = occupation,
        avatar = avatar,
        bio = bio,
        backdrop = backdrop
    )
}

fun AnimeResponse.asEntity(): AnimeEntity {
    return AnimeEntity(
        malId = malId,
        title = title,
        imageUrl = images.webp.imageUrl,
        synopsis = synopsis
    )
}

fun AnimeEntity.asDomain(): AnimeDomain {
    return AnimeDomain(
        malId = malId,
        title = title,
        imageUrl = imageUrl
    )
}

fun AnimeEntity.asDetailDomain(): AnimeDetailDomain {
    return AnimeDetailDomain(
        malId = malId,
        title = title,
        imageUrl = imageUrl,
        synopsis = synopsis
    )
}

fun AnimeDomain.asPresentation(): Anime {
    return Anime(
        malId = malId,
        title = title,
        imageUrl = imageUrl
    )
}

fun AnimeDetailDomain.asPresentation(): AnimeDetail {
    return AnimeDetail(
        malId = malId,
        title = title,
        imageUrl = imageUrl,
        synopsis = synopsis ?: "-"
    )
}