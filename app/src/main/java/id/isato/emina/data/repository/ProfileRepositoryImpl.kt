package id.isato.emina.data.repository

import id.isato.emina.di.IoDispatcher
import id.isato.emina.domain.model.ProfileDomain
import id.isato.emina.domain.repository.ProfileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ProfileRepository {

    override fun getProfile(): Flow<ProfileDomain> = flow {
        emit(
            ProfileDomain(
                userName = "Iman",
                occupation = "Android Engineer",
                avatar = "https://github.com/i-sato/repo/blob/main/photo.jpg?raw=true",
                bio = "Hi! I'm Iman, working as Android Developer. Enthusiast in creating app from scratch using the latest technology.",
                backdrop = "https://www.neverendingvoyage.com/wp-content/uploads/2017/11/takayama-street.jpg"
            )
        )
    }.flowOn(ioDispatcher)

}