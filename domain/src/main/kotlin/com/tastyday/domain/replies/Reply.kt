package com.tastyday.domain.replies

import com.tastyday.common.utils.IdGenerator

class Reply private constructor(
    val id: String,
    val commentId: Long,
    parent: Reply?,
    val userId: Long,
    mentionUserIds: MentionUserIds?,
    content: Content,
    children: Children?,
) {

    companion object {
        fun create(
            commentId: Long,
            parent: Reply?,
            userId: Long,
            mentionUserIds: MentionUserIds?,
            content: String,
            children: MutableList<Reply>?,
        ) = Reply(
            id = IdGenerator.createId(),
            commentId = commentId,
            parent = parent,
            userId = userId,
            mentionUserIds = mentionUserIds,
            content = Content(content),
            children = Children(children)
        )
    }

    fun assignParent(parent: Reply) {
        if (this.parent != null) {
            this.parent?.children?.remove(this)
        }
        this.parent = parent
        this.parent?.children?.add(this)
    }

    var parent: Reply? = parent
        private set

    var mentionUserIds: MentionUserIds? = mentionUserIds
        private set

    var content: Content = content
        private set

    var children: Children? = children
        private set

    fun changeMentionUserId(mentionUserIds: List<Long>?) {
        this.mentionUserIds = MentionUserIds(mentionUserIds)
    }

    fun changeContent(content: String) {
        this.content = Content(content)
    }

}