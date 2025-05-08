package com.putriwidianingsih607062330057.asesment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.putriwidianingsih607062330057.asesment2.navigation.SetupNavGraph
import com.putriwidianingsih607062330057.asesment2.ui.screen.MainScreen
import com.putriwidianingsih607062330057.asesment2.ui.theme.PeminjamanBukuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PeminjamanBukuTheme {
                SetupNavGraph()
            }
        }
    }
}