package com.tastyday.domain.posts

import com.tastyday.common.utils.IdGenerator

class Post private constructor(
    private val id: String,
    private val content: String,
    private val userId: String
) {
    companion object {
        fun create(content: String, userId: String): Post {
            return Post(IdGenerator.createId(), content, userId)
        }
    }
}