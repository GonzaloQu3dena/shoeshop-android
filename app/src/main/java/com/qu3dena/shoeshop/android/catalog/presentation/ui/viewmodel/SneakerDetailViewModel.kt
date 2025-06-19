package com.qu3dena.shoeshop.android.catalog.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qu3dena.shoeshop.android.catalog.domain.model.Sneaker
import com.qu3dena.shoeshop.android.catalog.domain.usecases.GetSneakerByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SneakerDetailUiState(
    val isLoading: Boolean = false,
    val sneaker: Sneaker? = null,
    val error: String? = null
)

@HiltViewModel
class SneakerDetailViewModel @Inject constructor(
    private val getSneakerByIdUseCase: GetSneakerByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SneakerDetailUiState())
    val uiState: StateFlow<SneakerDetailUiState> = _uiState.asStateFlow()

    fun fetchSneakerById(id: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                getSneakerByIdUseCase.invoke(id).collect { sneaker ->
                    _uiState.update { it.copy(isLoading = false, sneaker = sneaker) }
                }
            }
            catch (ex: Exception) {
                _uiState.update { it.copy(isLoading = false, error = ex.message) }
            }
        }
    }
}