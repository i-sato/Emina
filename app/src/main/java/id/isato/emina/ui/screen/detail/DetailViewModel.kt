package id.isato.emina.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.isato.emina.domain.usecase.AnimeUseCase
import id.isato.emina.ui.common.UiState
import id.isato.emina.ui.model.AnimeDetail
import id.isato.emina.utils.asPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: AnimeUseCase
) : ViewModel() {

    private val _detailState: MutableStateFlow<UiState<AnimeDetail>> =
        MutableStateFlow(UiState.Loading)
    val detailState: StateFlow<UiState<AnimeDetail>>
        get() = _detailState

    fun getAnimeById(malId: Int) {
        viewModelScope.launch {
            _detailState.value = UiState.Loading
            _detailState.value = UiState.Success(
                useCase.getAnimeById(malId).asPresentation()
            )
        }
    }

    fun setFavorite(malId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            useCase.updateFavorite(malId, isFavorite)
        }
    }

}