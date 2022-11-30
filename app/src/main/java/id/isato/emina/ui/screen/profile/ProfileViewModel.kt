package id.isato.emina.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.isato.emina.domain.usecase.ProfileUseCase
import id.isato.emina.ui.common.UiState
import id.isato.emina.ui.model.Profile
import id.isato.emina.utils.asPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCase: ProfileUseCase
) : ViewModel() {

    private val _profileState: MutableStateFlow<UiState<Profile>> =
        MutableStateFlow(UiState.Loading)
    val profileState: StateFlow<UiState<Profile>>
        get() = _profileState

    fun getProfile() {
        viewModelScope.launch {
            useCase.getProfile()
                .map { it.asPresentation() }
                .catch {
                    _profileState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _profileState.value = UiState.Success(it)
                }
        }
    }

}