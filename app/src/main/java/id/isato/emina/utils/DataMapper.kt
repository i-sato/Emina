package id.isato.emina.utils

import id.isato.emina.domain.model.ProfileDomain
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