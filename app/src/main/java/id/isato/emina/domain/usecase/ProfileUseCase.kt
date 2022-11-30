package id.isato.emina.domain.usecase

import id.isato.emina.domain.model.ProfileDomain
import kotlinx.coroutines.flow.Flow

interface ProfileUseCase {

    fun getProfile(): Flow<ProfileDomain>

}