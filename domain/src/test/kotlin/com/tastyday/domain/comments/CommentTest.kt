package com.tastyday.domain.comments

import com.tastyday.domain.comments.FixtureComment.Companion.aComment
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CommentTest {

    @Test
    internal fun `댓글 생성 테스트`() {
        val expectedPostId = "PostId"
        val expectedTargetUserId = 1L
        val content = "게시글 진짜 웃기네요 ㅋㅋㅋ"
        val newComment = aComment(content)

        assertThat(newComment.id).isNotEmpty
        assertThat(newComment.postId).isEqualTo(expectedPostId)
        assertThat(newComment.targetUserId).isEqualTo(expectedTargetUserId)
        assertThat(newComment.userId).isEqualTo(1L)
        assertThat(newComment.content.toString()).isEqualTo(content)
    }

    @Test
    internal fun `타켓 유저 수정`() {
        val newComment = aComment("게시글 진짜 웃기네요 ㅋㅋㅋ")

        val expectedTargetUserId = 2L
        newComment.changeTargetUserId(expectedTargetUserId)
        assertThat(newComment.targetUserId).isEqualTo(expectedTargetUserId)
    }

    @Test
    internal fun `댓글 내용 수정`() {
        val newComment = aComment("게시글 진짜 웃기네요 ㅋㅋㅋ")

        val updateContent = "댓글 수정해야지!!"
        newComment.changeContent(updateContent)
        assertThat(newComment.content.toString()).isEqualTo(updateContent)
    }

}

class FixtureComment {
    companion object {
        fun aComment(content: String) = Comment.create(
                userId = 1L,
                postId = "PostId",
                targetUserId = 1L,
                content = content
        )
    }
}