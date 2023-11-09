package ru.dm.myapps.clienvk.presentation.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.dm.myapps.clienvk.R
import ru.dm.myapps.clienvk.domain.enity.FeedPost
import ru.dm.myapps.clienvk.domain.enity.StatisticItem
import ru.dm.myapps.clienvk.domain.enity.StatisticType
import ru.dm.myapps.clienvk.ui.theme.RedHeart

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onCommentsItemClickListener: () -> Unit,
    onLikeItemClickListener: () -> Unit,
    isLiked: Boolean
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {

            PostHeader(feedPost)
            PostText(feedPost)
            MainImage(feedPost)
            Statistic(
                statisticItems = feedPost.statisticItems,
                onCommentClickListener = onCommentsItemClickListener,
                onLikeClickListener = onLikeItemClickListener,
                isFavourite = isLiked
            )
        }
    }
}

@Composable
private fun PostHeader(
    feedPost: FeedPost
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            model = feedPost.communityImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
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
    if (feedPost.contentText.isNotBlank()) {
        PostSpacer()
    }
}

@Composable
private fun MainImage(
    feedPost: FeedPost
) {
    AsyncImage(
        model = feedPost.contentImageUrl,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentScale = ContentScale.FillWidth
    )
    PostSpacer(15.dp)
}

@Composable
private fun Statistic(
    statisticItems: Map<StatisticType, StatisticItem>,
    onCommentClickListener: () -> Unit,
    onLikeClickListener: () -> Unit,
    isFavourite: Boolean

) {

    Row {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            IconWithText(
                iconResId = R.drawable.eye_24,
                text = convertCountToViewLayer(statisticItems[StatisticType.VIEWS]?.count ?: 0)
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconWithText(
                iconResId = R.drawable.share,
                text = convertCountToViewLayer(statisticItems[StatisticType.SHARES]?.count ?: 0)

            )
            IconWithText(
                iconResId = R.drawable.comment_24,
                text = convertCountToViewLayer(statisticItems[StatisticType.COMMENTS]?.count ?: 0),
                onClickListener = { onCommentClickListener() }
            )
            IconWithText(
                iconResId = if (isFavourite) {
                    R.drawable.like_filled
                } else {
                    R.drawable.like_106
                },
                text = convertCountToViewLayer(statisticItems[StatisticType.LIKES]?.count ?: 0),
                onClickListener = { onLikeClickListener() },
                tint = if (isFavourite) RedHeart else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onClickListener: (() -> Unit)? = null,
    tint: Color = MaterialTheme.colorScheme.onSurface
) {
    val modifier: Modifier = if (onClickListener == null) {
        Modifier
    } else {
        Modifier
            .fillMaxHeight()
            .clickable { onClickListener() }
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
    ) {
        Text(text = text)
        Spacer(Modifier.width(2.dp))
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = tint
        )
        PostSpacer(5.dp)
    }

}

@Composable
private fun PostSpacer(height: Dp = 6.dp) {
    Spacer(modifier = Modifier.height(height))
}


private fun convertCountToViewLayer(count: Int): String {
    return if (count > 100_000)
        "${count / 1000}K"
    else if (count > 1000)
        String.format("%.1fK", count / 1000f)
    else
        "$count"
}
