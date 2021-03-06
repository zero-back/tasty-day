package com.tastyday.domain.comments

import com.tastyday.common.utils.IdGenerator

class Comment private constructor(
        val id: String,
        val postId: String,
        val userId: Long?,
        mentionUserIds: List<Long>?,
        content: Content
) {
    companion object {
        fun create(userId: Long, postId: String, mentionUserIds: List<Long>?, content: String) = Comment(
                id = IdGenerator.createId(),
                postId = postId,
                userId = userId,
                mentionUserIds = mentionUserIds,
                content = Content(content)
        )
    }

    var mentionUserIds: List<Long>? = mentionUserIds
        private set

    var content: Content = content
        private set

    fun changeMentionUserId(mentionUserIds: List<Long>?) {
        this.mentionUserIds = mentionUserIds
    }

    fun changeContent(content: String) {
        this.content = Content(content)
    }

}