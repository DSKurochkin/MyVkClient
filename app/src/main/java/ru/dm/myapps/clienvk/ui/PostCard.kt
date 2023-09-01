package ru.dm.myapps.clienvk.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dm.myapps.clienvk.R
import ru.dm.myapps.clienvk.ui.theme.ClienVKTheme

@Composable
fun PostCard() {

    Card(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxHeight()
            .padding(6.dp)


    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp)


        ) {

            PostHeader()
            PostText()
            MainImage()
            Statistic()
        }
    }

}

@Composable
private fun PostHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = R.drawable.soccer_24),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(3.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.sample_group_name),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = stringResource(R.string.sample_post_time))
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
    MainSpacer()
}

@Composable
private fun PostText() {
    Text(
        // modifier = Modifier.padding(start = 5.dp, end = 5.dp),
        text = stringResource(id = R.string.sample_post)
    )
    MainSpacer()
}

@Composable
private fun MainImage() {
    Image(
        painter = painterResource(id = R.drawable.pic),
        contentDescription = null,
        modifier = Modifier
            .height(560.dp)
            .fillMaxSize(),

        //  contentScale = ContentScale.FillWidth
    )
    MainSpacer()
}

@Composable
private fun Statistic() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 7.dp, end = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            IconWithText(iconResId = R.drawable.eye_24, text = "250")
        }

        Row(
            modifier = Modifier.weight(1f)
        ) {
            IconWithText(iconResId = R.drawable.share, text = "300")
            IconWithText(iconResId = R.drawable.comment_24, text = "500")
            IconWithText(iconResId = R.drawable.like_106, text = "404")
        }
    }
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String
) {
    Row(
        modifier = Modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = text)
        Spacer(Modifier.width(2.dp))
        Icon(painterResource(id = iconResId), contentDescription = null)
        Spacer(Modifier.width(5.dp))
    }

}

@Composable
private fun MainSpacer() {
    Spacer(modifier = Modifier.height(5.dp))
}


@Preview
@Composable
private fun TestLightTheme() {
    ClienVKTheme(darkTheme = false) {
        PostCard()
    }
}

@Preview
@Composable
private fun TestDarkTheme() {
    ClienVKTheme(darkTheme = true) {
        PostCard()
    }
}


