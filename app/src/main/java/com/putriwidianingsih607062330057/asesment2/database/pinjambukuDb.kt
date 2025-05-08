package com.putriwidianingsih607062330057.asesment2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.putriwidianingsih607062330057.asesment2.model.Buku
import com.putriwidianingsih607062330057.asesment2.model.Peminjaman

@Database(entities = [Buku::class, Peminjaman::class], version = 1, exportSchema = false)
abstract class pinjambukuDb : RoomDatabase() {
    abstract fun bukuDao(): BukuDao
    abstract fun peminjamanDao(): PeminjamanDao

    companion object {

        @Volatile
        private var INSTANCE: pinjambukuDb? = null

        fun getInstance(context: Context): pinjambukuDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    pinjambukuDb::class.java,
                    "peminjaman_buku.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
