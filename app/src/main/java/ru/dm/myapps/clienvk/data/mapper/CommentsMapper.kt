package ru.dm.myapps.clienvk.data.mapper

import ru.dm.myapps.clienvk.data.model.comments.CommentsResponse
import ru.dm.myapps.clienvk.domain.enity.Comment

class CommentsMapper : AppMapper() {
    fun responseToComments(response: CommentsResponse): List<Comment> {
        val res = mutableListOf<Comment>()
        val profilesDtos = response.content.profiles
        val commentsDtos = response.content.comments
        for (dto in commentsDtos) {
            val profile = profilesDtos.find { dto.authorId == it.id } ?: continue
            if (dto.text.isEmpty()) continue
            res.add(
                Comment(
                    id = dto.id,
                    authorName = "${profile.name} ${profile.surname}",
                    authorAvatarUrl = profile.avatarUrl,
                    text = dto.text,
                    publicationDate = convertDate(dto.publicationDate)
                )
            )
        }

        return res
    }

}