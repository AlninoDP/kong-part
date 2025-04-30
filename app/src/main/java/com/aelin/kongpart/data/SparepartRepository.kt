package com.aelin.kongpart.data

import com.aelin.kongpart.model.DummySparepartDatasource
import com.aelin.kongpart.model.Sparepart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SparepartRepository {

    private val spareparts = mutableListOf<Sparepart>()

    init {
        if (spareparts.isEmpty()){
            spareparts.addAll(DummySparepartDatasource.dummySparepart)
        }
    }

    fun getAllSpareparts(): Flow<List<Sparepart>> {
        return flowOf(spareparts)
    }

    fun getSparepartByCategory(category: String): List<Sparepart> {
        return spareparts.filter { it.category == category }
    }

    fun getSparepartById(id: Int): Sparepart {
        return spareparts.first(){
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: SparepartRepository? = null

        fun getInstance(): SparepartRepository =
            instance ?: synchronized(this) {
                SparepartRepository().apply {
                    instance = this
                }
            }
    }
}