package com.tastyday.domain.comments

import com.tastyday.domain.comments.FixtureComment.Companion.aComment
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CommentTest {

    @Test
    internal fun `댓글 생성 테스트`() {
        val expectedPostId = "PostId"
        val expectedTargetUserIds = listOf(1L, 2L)
        val content = "게시글 진짜 웃기네요 ㅋㅋㅋ"
        val newComment = aComment(
                mentionUserIds = expectedTargetUserIds,
                content = content
        )

        assertThat(newComment.id).isNotEmpty
        assertThat(newComment.postId).isEqualTo(expectedPostId)
        assertThat(newComment.mentionUserIds).isEqualTo(expectedTargetUserIds)
        assertThat(newComment.userId).isEqualTo(1L)
        assertThat(newComment.content.toString()).isEqualTo(content)
    }

    @Test
    internal fun `댓글 생성 테스트 - 멘션 유저 없음`() {
        val expectedPostId = "PostId"
        val content = "게시글 진짜 웃기네요 ㅋㅋㅋ"
        val newComment = aComment(
                mentionUserIds = emptyList(),
                content = content
        )

        assertThat(newComment.id).isNotEmpty
        assertThat(newComment.postId).isEqualTo(expectedPostId)
        assertThat(newComment.mentionUserIds).isEmpty()
        assertThat(newComment.userId).isEqualTo(1L)
        assertThat(newComment.content.toString()).isEqualTo(content)
    }

    @Test
    internal fun `멘션 유저 수정`() {
        val newComment = aComment(
                mentionUserIds = listOf(1L, 2L),
                content = "게시글 진짜 웃기네요 ㅋㅋㅋ"
        )
        val expectedMentionUserIds = listOf(3L, 4L)
        newComment.changeMentionUserId(expectedMentionUserIds)
        assertThat(newComment.mentionUserIds).isEqualTo(expectedMentionUserIds)
    }

    @Test
    internal fun `멘션 유저는 없다`() {
        val newComment = aComment(
                mentionUserIds = emptyList(),
                content = "게시글 진짜 웃기네요 ㅋㅋㅋ"
        )

        val expectedMentionUserIds = emptyList<Long>()
        newComment.changeMentionUserId(expectedMentionUserIds)
        assertThat(newComment.mentionUserIds).isEqualTo(expectedMentionUserIds)
    }

    @Test
    internal fun `댓글 내용 수정`() {
        val newComment = aComment(
                mentionUserIds = listOf(1L, 2L),
                content = "게시글 진짜 웃기네요 ㅋㅋㅋ"
        )
        val updateContent = "댓글 수정해야지!!"
        newComment.changeContent(updateContent)
        assertThat(newComment.content.toString()).isEqualTo(updateContent)
    }

}

class FixtureComment {
    companion object {
        fun aComment(mentionUserIds: List<Long>, content: String) = Comment.create(
                userId = 1L,
                postId = "PostId",
                mentionUserIds = mentionUserIds,
                content = content
        )
    }
}