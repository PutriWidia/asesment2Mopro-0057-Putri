package com.putriwidianingsih607062330057.asesment2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putriwidianingsih607062330057.asesment2.database.PeminjamanDao
import com.putriwidianingsih607062330057.asesment2.model.Peminjaman
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(dao: PeminjamanDao) : ViewModel() {
    val data: StateFlow<List<Peminjaman>> = dao.getPeminjaman().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )
}