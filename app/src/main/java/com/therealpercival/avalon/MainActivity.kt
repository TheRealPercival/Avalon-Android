package com.therealpercival.avalon

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.therealpercival.avalon.domain.repository.UserRepository
import com.therealpercival.avalon.presentation.AvalonNavigation
import com.therealpercival.avalon.presentation.ui.theme.AvalonTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        intent?.let { userRepository.handleDeepLink(it) }
        
        setContent {
            AvalonTheme {
                AvalonNavigation()
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        userRepository.handleDeepLink(intent)
    }
}
