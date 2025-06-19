package com.qu3dena.shoeshop.android.catalog.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qu3dena.shoeshop.android.catalog.domain.model.Sneaker
import com.qu3dena.shoeshop.android.catalog.domain.usecases.GetFavoriteSneakersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavoriteSneakersListUiState(
    val isLoading: Boolean = false,
    val sneakers: List<Sneaker> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class FavoriteSneakersListViewModel @Inject constructor(
    private val getFavoriteSneakersUseCase: GetFavoriteSneakersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteSneakersListUiState())
    val uiState: StateFlow<FavoriteSneakersListUiState> = _uiState.asStateFlow()

    init {
        fetchFavoriteSneakers()
    }

    fun fetchFavoriteSneakers() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                getFavoriteSneakersUseCase.invoke().collect { favorites ->
                    _uiState.update {
                        it.copy(isLoading = false, sneakers = favorites)
                    }
                }
            } catch (ex: Exception) {
                _uiState.update { it ->
                    it.copy(isLoading = false, error = ex.message)
                }
            }
        }
    }
}