package com.tastyday.domain.posts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PostTest {
    @Test
    internal fun `게시글_생성_테스트`() {
        val post = Post.create("내용", "USER_ID")
        assertThat(post).isNotNull
    }
}