package com.putriwidianingsih607062330057.asesment2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.putriwidianingsih607062330057.asesment2.model.Buku

@Dao
interface BukuDao {
    @Insert
    suspend fun insertBuku(buku: Buku)

    @Query("SELECT * FROM buku WHERE isDelete = 0")
    fun getAllBuku(): List<Buku>

    @Query("UPDATE buku SET isDelete = 1 WHERE id = :bukuId")
    suspend fun softDeleteBuku(bukuId: Int)

    @Query("UPDATE buku SET isDelete = 0 WHERE id = :bukuId")
    suspend fun restoreBuku(bukuId: Int)
}