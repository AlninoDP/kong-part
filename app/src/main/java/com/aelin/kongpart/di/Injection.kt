package com.aelin.kongpart.di

import com.aelin.kongpart.data.SparepartRepository

object Injection {
    fun provideRepository(): SparepartRepository{
        return SparepartRepository.getInstance()
    }
}