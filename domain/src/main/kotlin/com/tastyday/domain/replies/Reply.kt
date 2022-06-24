package com.tastyday.domain.replies

import com.tastyday.common.utils.IdGenerator

class Reply private constructor(
        val id: String,
        val commentId: Long,
        val parentReplyId: Long?,
        val userId: Long,
        mentionUserIds: List<Long>?,
        content: Content
) {

    companion object {
        fun create(commentId: Long, parentReplyId: Long?, userId: Long, mentionUserIds: List<Long>?, content: String) = Reply(
                id = IdGenerator.createId(),
                commentId = commentId,
                parentReplyId = parentReplyId,
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