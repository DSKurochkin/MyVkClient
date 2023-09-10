package ru.dm.myapps.clienvk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import ru.dm.myapps.clienvk.ui.MainScreen
import ru.dm.myapps.clienvk.ui.MainViewModel
import ru.dm.myapps.clienvk.ui.theme.ClienVKTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClienVKTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }
}
