package com.tastyday.domain.replies

class MentionUserIds(
    private val mentionUserIds: List<Long>?,
) {

    var to: List<Long>? = mentionUserIds
        get() = this.mentionUserIds?.toList() ?: emptyList()

}