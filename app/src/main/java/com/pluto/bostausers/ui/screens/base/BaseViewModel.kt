package com.pluto.bostausers.ui.screens.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : BaseUiState>(state: UiState) : ViewModel() {

    private val _state = MutableStateFlow(state)
    val state = _state.asStateFlow()

    fun <T> tryToExecute(
        function: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (message: String) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ){
        viewModelScope.launch(dispatcher) {
            try {
                val result = function()
                onSuccess(result)
            }catch (e: Exception){
                onError(e.message.orEmpty())
            }
        }
    }
}

interface BaseUiState
interface BaseUiEffect