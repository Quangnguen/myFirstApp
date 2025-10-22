package com.example.base_app.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base_app.domain.model.User
import com.example.base_app.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    init {
        Log.d("HomeViewModel", "Initializing HomeViewModel")
        fetchCurrentUser()
    }

    private fun fetchCurrentUser() {
        viewModelScope.launch {
            Log.d("HomeViewModel", "Fetching current user")
            try {
                _currentUser.value = authRepository.getCurrentUser()
                Log.d("HomeViewModel", "Current user: ${_currentUser.value}")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching user: ${e.message}")
                _currentUser.value = null
            }
        }
    }
}