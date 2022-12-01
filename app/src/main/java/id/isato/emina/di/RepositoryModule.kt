package id.isato.emina.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.isato.emina.data.repository.AnimeRepositoryImpl
import id.isato.emina.data.repository.ProfileRepositoryImpl
import id.isato.emina.domain.repository.AnimeRepository
import id.isato.emina.domain.repository.ProfileRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindProfileRepository(repository: ProfileRepositoryImpl): ProfileRepository

    @Binds
    abstract fun bindAnimeRepository(repository: AnimeRepositoryImpl): AnimeRepository

}