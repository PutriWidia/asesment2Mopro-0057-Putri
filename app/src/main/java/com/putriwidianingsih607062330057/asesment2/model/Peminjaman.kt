package com.putriwidianingsih607062330057.asesment2.model

import androidx.room.Delete
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peminjaman")
data class Peminjaman(
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
    val idBuku: Int,
    val namaPeminjaman: String,
    val tanggalPinjam: String,
    val tanggalKembali: String,
    val isDelete: Boolean = false
)
