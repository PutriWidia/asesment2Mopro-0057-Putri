package com.putriwidianingsih607062330057.asesment2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Peminjaman")
data class Peminjaman(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val namaPeminjam: String,
    val judul: String,
    val tanggalPinjam: String,
    val tanggalKembali: String,
    val jumlahHari: Int
)