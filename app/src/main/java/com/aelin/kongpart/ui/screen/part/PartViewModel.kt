package com.aelin.kongpart.ui.screen.part

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aelin.kongpart.data.SparepartRepository
import com.aelin.kongpart.model.Sparepart
import com.aelin.kongpart.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PartViewModel (private val repository: SparepartRepository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Sparepart>>> = MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<List<Sparepart>>> get() = _uiState

    fun getSparepartByCategory(category: String){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getSparepartByCategory(category))
        }
    }
}