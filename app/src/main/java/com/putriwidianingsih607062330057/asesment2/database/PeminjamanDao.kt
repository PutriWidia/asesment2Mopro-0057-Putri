package com.putriwidianingsih607062330057.asesment2.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.putriwidianingsih607062330057.asesment2.model.Buku
import com.putriwidianingsih607062330057.asesment2.model.Peminjaman
import kotlinx.coroutines.flow.Flow

@Dao
interface PeminjamanDao {
    @Query("SELECT * FROM peminjaman ORDER BY namaPeminjam ASC")
    fun getAllPeminjaman(): Flow<List<Peminjaman>>

    @Insert
    suspend fun insert(peminjaman: Peminjaman)

    @Update
    suspend fun update(peminjaman: Peminjaman)

    @Query("SELECT * FROM peminjaman WHERE id = :id")
    suspend fun getPeminjamanById(id: Int): Peminjaman?

    @Query("DELETE FROM peminjaman WHERE id = :id")
    suspend fun deletePeminjam(id: Int)
}