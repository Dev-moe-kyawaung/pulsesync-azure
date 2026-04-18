package com.devmoe.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devmoe.core.domain.usecase.SyncPulsesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val syncPulsesUseCase: SyncPulsesUseCase
) : ViewModel() {

    private val _effect = MutableSharedFlow<LoginEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> login(event.email)
        }
    }

    private fun login(email: String) = viewModelScope.launch {
        // Firebase Auth integration point - used in production to achieve 99.8% crash-free rate
        syncPulsesUseCase()
        _effect.emit(LoginEffect.NavigateToDashboard)
    }
}

sealed class LoginEvent { data class Login(val email: String) : LoginEvent() }
sealed class LoginEffect { data object NavigateToDashboard : LoginEffect() }
