package com.qu3dena.shoeshop.android.catalog.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qu3dena.shoeshop.android.catalog.domain.model.Sneaker
import com.qu3dena.shoeshop.android.catalog.domain.usecases.DeleteFavoriteUseCase
import com.qu3dena.shoeshop.android.catalog.domain.usecases.GetAllSneakersUseCase
import com.qu3dena.shoeshop.android.catalog.domain.usecases.SaveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SneakerListUiState(
    val isLoading: Boolean = false,
    val sneakers: List<Sneaker> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class SneakerListViewModel @Inject constructor(
    private val getAllSneakersUseCase: GetAllSneakersUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SneakerListUiState())
    val uiState: StateFlow<SneakerListUiState> = _uiState.asStateFlow()

    init {
        fetchSneakers()
    }

    fun fetchSneakers() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                getAllSneakersUseCase.invoke().collect { sneakers ->
                    _uiState.update {
                        it.copy(isLoading = false, sneakers = sneakers)
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it ->
                    it.copy(isLoading = false, error = e.message)
                }
            }
        }
    }

    fun toggleFavorite(sneaker: Sneaker) {
        viewModelScope.launch {
            if (sneaker.isFavorite) deleteFavoriteUseCase.invoke(sneaker).collect{ }
            else saveFavoriteUseCase.invoke(sneaker).collect { }

        }
        fetchSneakers()
    }
}