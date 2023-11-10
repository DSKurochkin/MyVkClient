package ru.dm.myapps.clienvk.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.dm.myapps.clienvk.domain.NewsFeedRepository
import ru.dm.myapps.clienvk.domain.enity.Comment
import ru.dm.myapps.clienvk.domain.enity.FeedPost
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(post: FeedPost): Flow<List<Comment>> {
        return repository.getComments(post)
    }

}