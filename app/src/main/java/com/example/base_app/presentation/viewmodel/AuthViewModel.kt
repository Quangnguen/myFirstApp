package com.example.base_app.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base_app.domain.usecase.LoginUseCase
import com.example.base_app.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val result = loginUseCase(email, password)
            _uiState.value = when {
                result.isSuccess -> _uiState.value.copy(
                    isLoading = false,
                    success = true,
                    error = null
                )
                else -> _uiState.value.copy(
                    isLoading = false,
                    success = false,
                    error = result.exceptionOrNull()?.message
                )
            }
        }
    }

    fun register(email: String, name: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val result = registerUseCase(email, name, password)
            _uiState.value = when {
                result.isSuccess -> _uiState.value.copy(
                    isLoading = false,
                    success = true,
                    error = null
                )
                else -> _uiState.value.copy(
                    isLoading = false,
                    success = false,
                    error = result.exceptionOrNull()?.message
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

data class AuthUiState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)