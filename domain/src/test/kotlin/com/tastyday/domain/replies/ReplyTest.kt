package com.tastyday.domain.replies

import com.tastyday.domain.replies.FixtureReply.Companion.aReply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReplyTest {

    @Test
    internal fun `답글 생성 테스트`() {
        val expectedUserId = 1L
        val expectedContent = "저도 그렇게 생각합니다!"
        val expectedTargetUserIds = listOf(1L, 2L, 3L)
        val expectedParentReplyId = 1L
        val expectedCommentId = 1L
        val newReply = aReply(
                commentId = expectedCommentId,
                parentReplyId = expectedParentReplyId,
                userId = expectedUserId,
                targetUserIds = expectedTargetUserIds,
                content = expectedContent
        )
        assertThat(newReply.id).isNotNull
        assertThat(newReply.commentId).isEqualTo(expectedCommentId)
        assertThat(newReply.parentReplyId).isEqualTo(expectedParentReplyId)
        assertThat(newReply.userId).isEqualTo(expectedUserId)
        assertThat(newReply.mentionUserIds).isEqualTo(expectedTargetUserIds)
        assertThat(newReply.content.toString()).isEqualTo(expectedContent)
    }

    @Test
    internal fun `답글 생성 테스트 - 멘션 유저 없음`() {
        val expectedUserId = 1L
        val expectedContent = "저도 그렇게 생각합니다!"
        val expectedParentReplyId = 1L
        val expectedCommentId = 1L
        val newReply = aReply(
                commentId = expectedCommentId,
                parentReplyId = expectedParentReplyId,
                userId = expectedUserId,
                targetUserIds = emptyList(),
                content = expectedContent
        )

        assertThat(newReply.id).isNotNull
        assertThat(newReply.commentId).isEqualTo(expectedCommentId)
        assertThat(newReply.parentReplyId).isEqualTo(expectedParentReplyId)
        assertThat(newReply.userId).isEqualTo(expectedUserId)
        assertThat(newReply.mentionUserIds).isEmpty()
        assertThat(newReply.content.toString()).isEqualTo(expectedContent)
    }

    @Test
    internal fun `멘션 유저 수정`() {
        val newReply = aReply(
                commentId = 1L,
                parentReplyId = 1L,
                userId = 1L,
                targetUserIds = listOf(1L, 2L, 3L),
                content = "재밌어요 ㅋㅋㅋ"
        )
        val updateMentionUserIds = listOf(1L, 2L, 8L, 9L)
        newReply.changeMentionUserId(updateMentionUserIds)
        assertThat(newReply.mentionUserIds).isEqualTo(updateMentionUserIds)
    }

    @Test
    internal fun `답글 내용 수정`() {
        val newReply = aReply(
                commentId = 1L,
                parentReplyId = 1L,
                userId = 1L,
                targetUserIds = listOf(1L, 2L, 3L),
                content = "재밌어요 ㅋㅋㅋ"
        )
        val updateContent = "답글 수정할래요!"
        newReply.changeContent(updateContent)
        assertThat(newReply.content.toString()).isEqualTo(updateContent)
    }

}

class FixtureReply {
    companion object {
        fun aReply(
                commentId: Long,
                parentReplyId: Long?,
                userId: Long,
                targetUserIds: List<Long>,
                content: String
        ) = Reply.create(
                commentId = commentId,
                parentReplyId = parentReplyId,
                userId = userId,
                mentionUserIds = targetUserIds,
                content = content
        )
    }
}