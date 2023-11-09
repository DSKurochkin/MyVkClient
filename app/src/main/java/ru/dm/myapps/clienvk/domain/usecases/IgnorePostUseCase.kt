package ru.dm.myapps.clienvk.domain.usecases

import ru.dm.myapps.clienvk.domain.NewsFeedRepository
import ru.dm.myapps.clienvk.domain.enity.FeedPost

class IgnorePostUseCase(private val repository: NewsFeedRepository) {
    suspend operator fun invoke(post: FeedPost) {
        repository.ignorePost(post)
    }

}