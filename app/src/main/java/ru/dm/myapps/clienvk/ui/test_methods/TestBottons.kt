package ru.dm.myapps.clienvk.ui.test_methods

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ru.dm.myapps.clienvk.R

@Composable
private fun BottomBar() {
    BottomAppBar() {


        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.square_20),
                    null
                )
            }
            // Spacer(modifier = Modifier.height(1.dp))
            Text(text = "News")
        }
        Icon(painter = painterResource(id = R.drawable.like_106), contentDescription = null)
        Icon(painter = painterResource(id = R.drawable.face_5), contentDescription = null)

    }
}


private val items = listOf("News", "Likes", "Face")

@Composable
private fun BottomBar2() {
    NavigationBar() {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(painter = painterResource(id = R.drawable.square_20), null) },
            label = { Text("News") }
        )


        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(painter = painterResource(id = R.drawable.like_106), null) },
            label = { Text("Like") }
        )


        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(painter = painterResource(id = R.drawable.face_5), null) },
            label = { Text("Follows") }
        )
    }
}