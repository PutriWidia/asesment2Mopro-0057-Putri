package com.putriwidianingsih607062330057.asesment2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putriwidianingsih607062330057.asesment2.database.PeminjamanDao
import com.putriwidianingsih607062330057.asesment2.model.Peminjaman
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel(private val dao: PeminjamanDao): ViewModel() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(namaPeminjam: String, judul: String) {
        val peminjaman = Peminjaman(
            tanggalPinjam = formatter.format(Date()),
            namaPeminjam = namaPeminjam,
            judul = judul
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(peminjaman)
        }
    }

    suspend fun getPeminjaman(id: Long): Peminjaman? {
        return dao.getPeminjamanById(id)
    }

    fun update(id: Long, namaPeminjam: String, judul: String) {
        val peminjaman = Peminjaman(
            id = id,
            tanggalPinjam = formatter.format(Date()),
            namaPeminjam = namaPeminjam,
            judul = judul
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(peminjaman)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deletePeminjaman(id)
        }
    }
}