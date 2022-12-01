package id.isato.emina.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.isato.emina.data.Resource
import id.isato.emina.domain.usecase.AnimeUseCase
import id.isato.emina.ui.common.UiState
import id.isato.emina.ui.model.Anime
import id.isato.emina.utils.asPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: AnimeUseCase
) : ViewModel() {

    private val _animeState: MutableStateFlow<UiState<List<Anime>>> =
        MutableStateFlow(UiState.Loading)
    val animeState: StateFlow<UiState<List<Anime>>>
        get() = _animeState

    fun getTopAnime() {
        viewModelScope.launch {
            useCase.getTopAnime().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val anime = resource.data?.map { it.asPresentation() }
                        anime?.let {
                            _animeState.value = UiState.Success(it)
                        }
                    }
                    is Resource.Error -> {
                        resource.message?.let {
                            _animeState.value = UiState.Error(it)
                        }
                    }
                }
            }
        }
    }

}