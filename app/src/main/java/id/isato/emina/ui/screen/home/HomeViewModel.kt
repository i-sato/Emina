package id.isato.emina.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.isato.emina.data.Resource
import id.isato.emina.domain.usecase.AnimeUseCase
import id.isato.emina.ui.common.UiState
import id.isato.emina.ui.model.Anime
import id.isato.emina.utils.asPresentation
import id.isato.emina.utils.ext.asStateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: AnimeUseCase
) : ViewModel() {

    private val _filter: MutableStateFlow<String> = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val animeState: StateFlow<UiState<List<Anime>>> = _filter.debounce(500).flatMapLatest {
        useCase.getTopAnime(it)
    }.map { resource ->
        when (resource) {
            is Resource.Success -> {
                val anime = resource.data?.map { it.asPresentation() }
                anime?.let {
                    UiState.Success(it)
                } ?: UiState.Error("Failed to fetch anime. Data is empty.")
            }
            is Resource.Loading -> UiState.Loading
            is Resource.Error -> {
                resource.message?.let {
                    UiState.Error(it)
                } ?: UiState.Error("Failed to fetch anime. Unknown Error.")
            }
        }
    }.asStateFlow(viewModelScope, UiState.Loading)

    fun filterAnime(animeTitle: String) {
        _filter.value = animeTitle
    }

}