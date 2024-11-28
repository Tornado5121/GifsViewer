package com.zhadko.gifyviewer.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : State> : ViewModel() {

    val initialState by lazy { initialState() }

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    abstract fun initialState(): S

    fun updateState(newState: S) {
        viewModelScope.launch {
            _state.update { newState }
        }
    }
}