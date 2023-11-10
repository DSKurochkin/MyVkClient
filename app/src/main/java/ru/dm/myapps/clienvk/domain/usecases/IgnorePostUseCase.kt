package ru.dm.myapps.clienvk.domain.usecases

import ru.dm.myapps.clienvk.domain.NewsFeedRepository
import ru.dm.myapps.clienvk.domain.enity.FeedPost
import javax.inject.Inject

class IgnorePostUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke(post: FeedPost) {
        repository.ignorePost(post)
    }

}