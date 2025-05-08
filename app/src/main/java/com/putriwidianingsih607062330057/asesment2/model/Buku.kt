package com.putriwidianingsih607062330057.asesment2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buku")
data class Buku(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val judul: String,
    val penulis: String,
    val kategori: String,
    val isDelete: Boolean = false
)
