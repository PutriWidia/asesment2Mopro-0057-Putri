package com.putriwidianingsih607062330057.asesment2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.putriwidianingsih607062330057.asesment2.model.Peminjaman

@Database(entities = [Peminjaman::class], version = 1, exportSchema = false)
abstract class PinjamBukuDb : RoomDatabase() {

    abstract fun peminjamanDao(): PeminjamanDao

    companion object {

        @Volatile
        private var INSTANCE: PinjamBukuDb? = null

        fun getInstance(context: Context): PinjamBukuDb? {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PinjamBukuDb::class.java,
                        "peminjaman.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
