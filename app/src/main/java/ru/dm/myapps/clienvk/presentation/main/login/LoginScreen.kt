package ru.dm.myapps.clienvk.presentation.main.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.dm.myapps.clienvk.R
import ru.dm.myapps.clienvk.ui.theme.vkBlue

@Composable
fun LoginScreen(singInCallback: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Image(painter = painterResource(id = R.drawable.vk_logo), contentDescription = null)
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = { singInCallback() },
            colors = ButtonDefaults.buttonColors(
                containerColor = vkBlue,
                contentColor = Color.White
            )
        ) {
            Text("Sing in")
        }
    }


}