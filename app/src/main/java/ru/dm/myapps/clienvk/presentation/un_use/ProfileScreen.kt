package ru.dm.myapps.clienvk.presentation.un_use

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {
    var count by rememberSaveable {
        mutableStateOf(0)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red), contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = "ProfileScreen", color = Color.White, fontSize = 30.sp)
            Text(
                modifier = Modifier.clickable { count++ },
                text = "Count: $count",
                color = Color.Black
            )
        }
    }
}


