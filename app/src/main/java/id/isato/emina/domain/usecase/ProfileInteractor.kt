package id.isato.emina.domain.usecase

import id.isato.emina.domain.model.ProfileDomain
import id.isato.emina.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val repository: ProfileRepository
): ProfileUseCase {

    override fun getProfile(): Flow<ProfileDomain> = repository.getProfile()

}