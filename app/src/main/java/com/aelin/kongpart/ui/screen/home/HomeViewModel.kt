package com.aelin.kongpart.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aelin.kongpart.data.SparepartRepository
import com.aelin.kongpart.model.Sparepart
import com.aelin.kongpart.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SparepartRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Sparepart>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Sparepart>>> get () = _uiState

    fun getAllSpareparts(){
        viewModelScope.launch {
            repository.getAllSpareparts()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{spareparts ->
                    _uiState.value = UiState.Success(spareparts)
                }
        }
    }
}