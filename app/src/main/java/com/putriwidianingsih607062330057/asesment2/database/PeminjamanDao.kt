package com.putriwidianingsih607062330057.asesment2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.putriwidianingsih607062330057.asesment2.model.Peminjaman
import kotlinx.coroutines.flow.Flow

@Dao
interface PeminjamanDao {

    @Insert
    suspend fun insert(peminjaman: Peminjaman)

    @Update
    suspend fun update(peminjaman: Peminjaman)

    @Query("SELECT * FROM peminjaman ORDER BY tanggalKembali ASC")
    fun getPeminjaman(): Flow<List<Peminjaman>>

    @Query("SELECT * FROM peminjaman WHERE id = :id")
    suspend fun getPeminjamanById(id: kotlin.Long): Peminjaman?

    @Query("DELETE FROM peminjaman WHERE id = :id")
    suspend fun deletePeminjaman(id: Long)
}