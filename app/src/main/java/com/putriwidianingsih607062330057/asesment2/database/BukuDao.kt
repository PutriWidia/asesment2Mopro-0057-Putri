package com.putriwidianingsih607062330057.asesment2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.putriwidianingsih607062330057.asesment2.model.Buku

@Dao
interface BukuDao {

    @Query("SELECT * FROM buku")
    suspend fun getAllBuku(): List<Buku>
}