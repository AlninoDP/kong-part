package com.aelin.kongpart.model

data class Sparepart (
    val id: Int,
    val name: String,
    val description: String,
    val stock: Int,
    val price: Double,
    val category: String,
    val imageUrl: String,
    val tags: List<String>
)