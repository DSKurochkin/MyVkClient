package ru.dm.myapps.clienvk.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import ru.dm.myapps.clienvk.domain.AuthState
import ru.dm.myapps.clienvk.presentation.getApplicationComponent
import ru.dm.myapps.clienvk.presentation.main.login.LoginScreen
import ru.dm.myapps.clienvk.presentation.main.login.LoginViewModel
import ru.dm.myapps.clienvk.ui.theme.ClienVKTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClienVKTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    val viewModel: LoginViewModel =
                        viewModel(factory = getApplicationComponent().getViewModelFactory())
                    val authState = viewModel.authState.collectAsState(AuthState.Initial)
                    val launcher = rememberLauncherForActivityResult(
                        contract = VK.getVKAuthActivityResultContract()
                    ) {
                        viewModel.performResult()
                    }

                    when (authState.value) {
                        is AuthState.Authorized -> {
                            MainScreen()
                        }

                        is AuthState.NotAuthorized -> LoginScreen {
                            launcher.launch(listOf(VKScope.WALL, VKScope.FRIENDS))
                        }

                        else -> {
                        }
                    }
                }
            }
        }
    }
}
