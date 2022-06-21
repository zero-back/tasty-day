package com.tastyday.domain.comments

import com.tastyday.common.utils.IdGenerator

class Comment private constructor(
        val id: String,
        val postId: String,
        val userId: Long?,
        targetUserId: Long?,
        content: Content
) {
    companion object {
        fun create(userId: Long, postId: String, targetUserId: Long?, content: String) = Comment(
                id = IdGenerator.createId(),
                postId = postId,
                userId = userId,
                targetUserId = targetUserId,
                content = Content(content)
        )
    }

    var targetUserId: Long? = targetUserId
        private set

    var content: Content = content
        private set

    fun changeTargetUserId(targetUserId: Long) {
        this.targetUserId = targetUserId
    }

    fun changeContent(content: String) {
        this.content = Content(content)
    }

}