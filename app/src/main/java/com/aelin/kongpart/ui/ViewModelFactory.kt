package com.aelin.kongpart.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aelin.kongpart.data.SparepartRepository
import com.aelin.kongpart.ui.screen.detail.DetailViewModel
import com.aelin.kongpart.ui.screen.home.HomeViewModel
import com.aelin.kongpart.ui.screen.part.PartViewModel

class ViewModelFactory(private val repository: SparepartRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (
            modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }else if (
            modelClass.isAssignableFrom(PartViewModel::class.java)) {
            return PartViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}