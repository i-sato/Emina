package id.isato.emina.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.isato.emina.domain.usecase.AnimeUseCase
import id.isato.emina.ui.model.Anime
import id.isato.emina.utils.asPresentation
import id.isato.emina.utils.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    useCase: AnimeUseCase
): ViewModel() {

    val favoriteState: StateFlow<List<Anime>> = useCase.getFavoriteAnime().map {
        it.map { anime -> anime.asPresentation() }
    }.asStateFlow(viewModelScope, emptyList())

}