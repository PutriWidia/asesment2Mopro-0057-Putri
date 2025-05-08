package com.putriwidianingsih607062330057.asesment2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.putriwidianingsih607062330057.asesment2.model.Buku
import com.putriwidianingsih607062330057.asesment2.model.Peminjaman

@Dao
interface PeminjamanDao {
    @Insert
    suspend fun insertPeminjaman(peminjaman: Peminjaman)

    @Query("SELECT * FROM buku WHERE isDelete = 0")
    fun getAllPeminjaman(): List<Peminjaman>

    @Query("UPDATE buku SET isDelete = 1 WHERE id = :peminjamanId")
    suspend fun softDeletePeminjaman(peminjamanId: Int)

    @Query("UPDATE buku SET isDelete = 0 WHERE id = :peminjamanId")
    suspend fun restorePeminjaman(peminjamanId: Int)
}