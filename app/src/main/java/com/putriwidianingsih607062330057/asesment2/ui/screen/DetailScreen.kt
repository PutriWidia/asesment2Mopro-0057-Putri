package com.putriwidianingsih607062330057.asesment2.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.putriwidianingsih607062330057.asesment2.R
import com.putriwidianingsih607062330057.asesment2.ui.theme.PeminjamanBukuTheme
import com.putriwidianingsih607062330057.asesment2.util.ViewModelFactory

const val KEY_ID_PEMINJAMAN = "idPeminjaman"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null){
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var namaPeminjam by remember { mutableStateOf("") }
    var judul by remember { mutableStateOf("") }
    var tanggalPinjam by remember { mutableStateOf("") }
    var tanggalKembali by remember { mutableStateOf("") }
    var jumlahHari by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getPeminjaman(id) ?: return@LaunchedEffect
        namaPeminjam = data.namaPeminjam
        judul = data.judul
        tanggalPinjam = data.tanggalPinjam
        tanggalKembali = data.tanggalKembali
        jumlahHari = data.jumlahHari.toString()
    }

    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null)
                        stringResource(id = R.string.tambahkan_peminjaman)
                    else
                        stringResource(id = R.string.edit_data)

                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {
                        if (namaPeminjam.isEmpty() || judul.isEmpty()) {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }

                        val jumlahHariTrimmed = jumlahHari.trim()
                        val jumlahHariInt = jumlahHariTrimmed.toIntOrNull()

                        if (jumlahHariTrimmed.isEmpty() || jumlahHariInt == null || jumlahHariInt <= 0) {
                            Toast.makeText(context, "Jumlah hari peminjaman harus diisi dan lebih dari 0", Toast.LENGTH_LONG).show()
                            return@IconButton
                        }

                        if (id == null) {
                            viewModel.insert(namaPeminjam, judul, tanggalKembali, jumlahHari)
                        } else {
                            viewModel.update(id, namaPeminjam, judul, tanggalKembali, jumlahHari)
                        }
                        navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null) {
                        DeleteAction {
                            showDialog = true
                        }
                    }
                }
            )
        }
    ) {
            padding ->
        FormCatatan(
            title = namaPeminjam,
            onTitleChange = {namaPeminjam = it},
            desc = judul,
            onDescChange = {judul = it},
//            date = tanggalKembali,
//            onDateChange = { tanggalKembali = it },
            jumlahHari = jumlahHari,
            onJumlahHariChange = { jumlahHari = it },
            modifier = Modifier.padding(padding)
        )

        if (id != null && showDialog) {
            DisplayAlertDialog(
                onDismissRequest = { showDialog = false }) {
                showDialog = false
                viewModel.delete(id)
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun DeleteAction(delete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.hapus))
                },
                onClick = {
                    expanded = false
                    delete()
                }
            )
        }
    }
}

@Composable
fun FormCatatan(
    title: String,onTitleChange: (String) -> Unit,
    desc: String,onDescChange:(String)-> Unit,
//    date: String, onDateChange: (String) -> Unit,
    jumlahHari: String, onJumlahHariChange: (String) -> Unit,

    modifier: Modifier
){
    Column (
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        OutlinedTextField(
            value = title,
            onValueChange = {onTitleChange(it)},
            label = { Text(text = stringResource(R.string.nama_peminjam)) },
            singleLine = true,
            keyboardOptions =  KeyboardOptions(
                capitalization= KeyboardCapitalization.Words,
                imeAction=ImeAction.Next

            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = desc,
            onValueChange = {onDescChange(it)},
            label = { Text(text= stringResource(R.string.judul)) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            ),
            modifier = Modifier.fillMaxWidth()
        )
//        OutlinedTextField(
//            value = date,
//            onValueChange = { onDateChange(it) },
//            label = { Text(text = stringResource(R.string.tanggal_kembali)) },
//            singleLine = true,
//            keyboardOptions = KeyboardOptions(
//                capitalization = KeyboardCapitalization.None,
//                imeAction = ImeAction.Done
//            ),
//            modifier = Modifier.fillMaxWidth()
//        )
        OutlinedTextField(
            value = jumlahHari,
            onValueChange = { onJumlahHariChange(it) },
            label = { Text(text = "Jumlah Hari") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    PeminjamanBukuTheme{
        DetailScreen(rememberNavController())
    }
}