package id.isato.emina.domain.repository

import id.isato.emina.domain.model.ProfileDomain
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getProfile(): Flow<ProfileDomain>

}