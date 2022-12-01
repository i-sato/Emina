package id.isato.emina.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.isato.emina.domain.usecase.AnimeInteractor
import id.isato.emina.domain.usecase.AnimeUseCase
import id.isato.emina.domain.usecase.ProfileInteractor
import id.isato.emina.domain.usecase.ProfileUseCase

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindProfileUseCase(useCase: ProfileInteractor): ProfileUseCase

    @Binds
    abstract fun bindAnimeUseCase(useCase: AnimeInteractor): AnimeUseCase

}