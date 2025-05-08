package com.putriwidianingsih607062330057.asesment2.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.putriwidianingsih607062330057.asesment2.database.PinjamBukuDb
import com.putriwidianingsih607062330057.asesment2.ui.screen.DetailViewModel
import com.putriwidianingsih607062330057.asesment2.ui.screen.MainViewModel

class ViewModelFactory (
    private val context: Context
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = PinjamBukuDb.getInstance(context)?.peminjamanDao()

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return dao?.let { MainViewModel(dao) } as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return dao?.let { DetailViewModel(dao) } as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}