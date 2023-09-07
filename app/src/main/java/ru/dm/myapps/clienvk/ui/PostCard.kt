package ru.dm.myapps.clienvk.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.dm.myapps.clienvk.R
import ru.dm.myapps.clienvk.domain.FeedPost
import ru.dm.myapps.clienvk.domain.StatisticItem
import ru.dm.myapps.clienvk.domain.StatisticType

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onViewsItemClickListener: (StatisticType) -> Unit,
    onSharedItemClickListener: (StatisticType) -> Unit,
    onCommentsItemClickListener: (StatisticType) -> Unit,
    onLikeItemClickListener: (StatisticType) -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxHeight()

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)


        ) {

            PostHeader(feedPost)
            PostText(feedPost)
            MainImage(feedPost)
            Statistic(
                feedPost.statisticItems,
                onViewsItemClickListener,
                onCommentsItemClickListener,
                onSharedItemClickListener,
                onLikeItemClickListener
            )
        }
    }

}

@Composable
private fun PostHeader(
    feedPost: FeedPost
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = feedPost.avatarResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(3.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = feedPost.communityName,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = feedPost.publicationDate)
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
    PostSpacer()
}

@Composable
private fun PostText(
    feedPost: FeedPost
) {
    Text(
        text = feedPost.contentText
    )

    PostSpacer()
}

@Composable
private fun MainImage(
    feedPost: FeedPost
) {
    Image(
        painter = painterResource(feedPost.contentImageResId),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
    )
    PostSpacer(15.dp)

}

@Composable
private fun Statistic(
    statisticItems: Map<StatisticType, StatisticItem>,
    onViewsClickListener: (StatisticType) -> Unit,
    onSharedClickListener: (StatisticType) -> Unit,
    onCommentClickListener: (StatisticType) -> Unit,
    onLikeClickListener: (StatisticType) -> Unit

) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            IconWithText(
                iconResId = R.drawable.eye_24,
                text = statisticItems[StatisticType.VIEWS]?.count.toString(),
                onClickListener = { onViewsClickListener(StatisticType.VIEWS) }
            )
        }

        Row(
            modifier = Modifier.weight(1f)
        ) {
            IconWithText(
                iconResId = R.drawable.share,
                text = statisticItems[StatisticType.SHARES]?.count.toString(),
                onClickListener = { onSharedClickListener(StatisticType.SHARES) }
            )
            IconWithText(
                iconResId = R.drawable.comment_24,
                text = statisticItems[StatisticType.COMMENTS]?.count.toString(),
                onClickListener = { onCommentClickListener(StatisticType.COMMENTS) }
            )
            IconWithText(
                iconResId = R.drawable.like_106,
                text = statisticItems[StatisticType.LIKES]?.count.toString(),
                onClickListener = { onLikeClickListener(StatisticType.LIKES) }
            )
        }
    }
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onClickListener: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .clickable { onClickListener() },
        verticalAlignment = Alignment.Top,
    ) {
        Text(text = text)
        Spacer(Modifier.width(2.dp))
        Icon(painterResource(id = iconResId), contentDescription = null)
        Spacer(Modifier.width(5.dp))
    }

}

@Composable
private fun PostSpacer(height: Dp = 5.dp) {
    Spacer(modifier = Modifier.height(height))
}


//@Preview
//@Composable
//private fun TestLightTheme() {
//    ClienVKTheme(darkTheme = false) {
//        PostCard()
//    }
//}
//
//@Preview
//@Composable
//private fun TestDarkTheme() {
//    ClienVKTheme(darkTheme = true) {
//        PostCard()
//    }
//}



