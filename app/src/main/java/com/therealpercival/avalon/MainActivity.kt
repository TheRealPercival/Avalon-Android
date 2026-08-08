package com.therealpercival.avalon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.therealpercival.avalon.presentation.AvalonNavigation
import com.therealpercival.avalon.presentation.ui.theme.AvalonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AvalonTheme {
                AvalonNavigation()
            }
        }
    }
}
