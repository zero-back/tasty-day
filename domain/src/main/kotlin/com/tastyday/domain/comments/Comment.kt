package com.tastyday.domain.comments

import com.tastyday.common.utils.IdGenerator

class Comment private constructor(
        val id: String,
        val postId: String,
        val userId: Long?,
        targetUserIds: List<Long>?,
        content: Content
) {
    companion object {
        fun create(userId: Long, postId: String, targetUserIds: List<Long>?, content: String) = Comment(
                id = IdGenerator.createId(),
                postId = postId,
                userId = userId,
                targetUserIds = targetUserIds,
                content = Content(content)
        )
    }

    var targetUserIds: List<Long>? = targetUserIds
        private set

    var content: Content = content
        private set

    fun changeTargetUserId(targetUserIds: List<Long>?) {
        this.targetUserIds = targetUserIds
    }

    fun changeContent(content: String) {
        this.content = Content(content)
    }

}