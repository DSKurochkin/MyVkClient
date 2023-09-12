package ru.dm.myapps.clienvk.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dm.myapps.clienvk.domain.Comment
import ru.dm.myapps.clienvk.navigation.Screen
import ru.dm.myapps.clienvk.navigation.rememberNavigationState
import ru.dm.myapps.clienvk.ui.theme.CommentsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable


fun CommentsScreen(commentsViewModel: CommentsViewModel) {
    val navigationState = rememberNavigationState()
    val commentsListState = commentsViewModel.commentList.observeAsState(listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(start = 20.dp),
                title = {
                    Text(text = "Comment for post")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.clickable { navigationState.navigateTo(Screen.NewsFeed.route) }
                    )
                }
            )
        }
    )
    {

        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        )
        {
            items(commentsListState.value, { it.id }) { comment ->
                Comment(comment = comment)
            }
        }


    }
}

@Composable
fun Comment(comment: Comment) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row {
            Icon(
                painter = painterResource(comment.authorAvatarResId),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Row {
                    Text(text = comment.authorName, fontSize = 15.sp)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "CommentId: ${comment.id}", fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = comment.text, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = comment.publicationDate, fontSize = 15.sp)
            }
        }
    }


}


@Composable
fun BiBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text("FooBar")
    }
}

///////////


//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable

//@Preview
//fun CommentsScreen() {
//    val navigationState = rememberNavigationState()
//    val list = mutableListOf<Comment>().apply {
//        repeat(30) {
//            add(genComment(it))
//        }
//    }.toList()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(8.dp)
//    ) {
//        TopAppBar(
//            modifier = Modifier.padding(start = 20.dp),
//            title = {
//                Text(text = "Comment for post")
//            },
//            navigationIcon = {
//                Icon(
//                    imageVector = Icons.Default.ArrowBack,
//                    contentDescription = null,
//                    modifier = Modifier.clickable { navigationState.navigateTo(Screen.NewsFeed.route) }
//                )
//            }
//        )
//
//        LazyColumn(modifier = Modifier
//            .padding(start = 8.dp, end = 8.dp),
//            verticalArrangement = Arrangement.spacedBy(12.dp))
//        {
//           items(list, { it.id }){comment->
//              Comment(comment = comment)
//           }
//        }
//
//    }
//}