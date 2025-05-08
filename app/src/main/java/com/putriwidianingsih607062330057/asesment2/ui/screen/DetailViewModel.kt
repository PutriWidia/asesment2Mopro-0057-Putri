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

    fun insert(namaPeminjam: String, judul: String, tanggalKembali: String, jumlahHari: String) {
        val tanggalPinjam = formatter.format(Date())
        val tanggalKembali = calculateTanggalKembali(tanggalPinjam, jumlahHari.toInt())

        val peminjaman = Peminjaman(
            tanggalPinjam = formatter.format(Date()),
                    tanggalKembali = calculateTanggalKembali(tanggalPinjam, jumlahHari.toInt()),
            namaPeminjam = namaPeminjam,
            judul = judul,
            jumlahHari = jumlahHari.toInt()
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(peminjaman)
        }
    }

    suspend fun getPeminjaman(id: Long): Peminjaman? {
        return dao.getPeminjamanById(id)
    }

    fun update(id: Long, namaPeminjam: String, judul: String, tanggalPinjam: String, jumlahHari: String) {
        val peminjaman = Peminjaman(
            id = id,
            tanggalPinjam = formatter.format(Date()),
            tanggalKembali = calculateTanggalKembali(tanggalPinjam, jumlahHari.toInt()),
            namaPeminjam = namaPeminjam,
            judul = judul,
            jumlahHari = jumlahHari.toInt()
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

    private fun calculateTanggalKembali(tanggalPinjam: String, jumlahHari: Int): String {
        val date = formatter.parse(tanggalPinjam)
        val calendar = java.util.Calendar.getInstance()
        calendar.time = date!!
        calendar.add(java.util.Calendar.DAY_OF_YEAR, jumlahHari)
        return formatter.format(calendar.time)
    }
}