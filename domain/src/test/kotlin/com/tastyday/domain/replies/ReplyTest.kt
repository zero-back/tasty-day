package com.tastyday.domain.replies

import com.tastyday.domain.replies.FixtureReply.Companion.aReply
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReplyTest {

    @Test
    internal fun `답글 생성 테스트`() {
        val expectedUserId = 1L
        val expectedContent = "저도 그렇게 생각합니다!"
        val expectedMentionUserIds = listOf(1L, 2L, 3L)
        val expectedParent = null
        val expectedCommentId = 1L
        val newReply = aReply(
            commentId = expectedCommentId,
            parent = expectedParent,
            userId = expectedUserId,
            mentionUserIds = expectedMentionUserIds,
            content = expectedContent,
            children = mutableListOf()
        )
        assertThat(newReply.id).isNotNull
        assertThat(newReply.commentId).isEqualTo(expectedCommentId)
        assertThat(newReply.parent).isEqualTo(expectedParent)
        assertThat(newReply.userId).isEqualTo(expectedUserId)
        assertThat(newReply.mentionUserIds?.to).isEqualTo(expectedMentionUserIds)
        assertThat(newReply.content.toString()).isEqualTo(expectedContent)
    }

    @Test
    internal fun `답글 생성 테스트 - 멘션 유저 없음`() {
        val expectedUserId = 1L
        val expectedContent = "저도 그렇게 생각합니다!"
        val expectedCommentId = 1L
        val newReply = aReply(
            commentId = expectedCommentId,
            parent = null,
            userId = expectedUserId,
            mentionUserIds = emptyList(),
            content = expectedContent,
            children = mutableListOf()
        )

        assertThat(newReply.id).isNotNull
        assertThat(newReply.commentId).isEqualTo(expectedCommentId)
        assertThat(newReply.userId).isEqualTo(expectedUserId)
        assertThat(newReply.mentionUserIds?.to).isEmpty()
        assertThat(newReply.content.toString()).isEqualTo(expectedContent)
    }

    @Test
    internal fun `멘션 유저 수정`() {
        val newReply = aReply(
            commentId = 1L,
            parent = null,
            userId = 1L,
            mentionUserIds = listOf(1L, 2L, 3L),
            content = "재밌어요 ㅋㅋㅋ",
            children = mutableListOf()
        )
        val updateMentionUserIds = listOf(1L, 2L, 8L, 9L)
        newReply.changeMentionUserId(updateMentionUserIds)
        assertThat(newReply.mentionUserIds?.to).isEqualTo(updateMentionUserIds)
    }

    @Test
    internal fun `답글 내용 수정`() {
        val newReply = aReply(
            commentId = 1L,
            parent = null,
            userId = 1L,
            mentionUserIds = listOf(1L, 2L, 3L),
            content = "재밌어요 ㅋㅋㅋ",
            children = mutableListOf()
        )
        val updateContent = "답글 수정할래요!"
        newReply.changeContent(updateContent)
        assertThat(newReply.content.toString()).isEqualTo(updateContent)
    }

    @Test
    internal fun `답글 여러개 등록`() {
        val newReply = aReply(
            commentId = 1L,
            parent = null,
            userId = 1L,
            mentionUserIds = listOf(1L, 2L, 3L),
            content = "재밌어요 ㅋㅋㅋ",
            children = mutableListOf()
        )

        val firstChild = aReply(
            commentId = 1L,
            parent = newReply,
            userId = 2L,
            mentionUserIds = listOf(4L, 5L),
            content = "윗 분 최고!",
            children = mutableListOf()
        )

        val secondChild = aReply(
            commentId = 1L,
            parent = firstChild,
            userId = 3L,
            mentionUserIds = listOf(2L),
            content = "그러니까요 ㅋㅋ",
            children = mutableListOf()
        )

        val thirdChild = aReply(
            commentId = 1L,
            parent = secondChild,
            userId = 4L,
            mentionUserIds = listOf(3L),
            content = "그런가요? ㅋㅋㅋ",
            children = mutableListOf()
        )

        firstChild.assignParent(newReply)
        secondChild.assignParent(firstChild)
        assertThat(firstChild.parent).isEqualTo(newReply)
        assertThat(secondChild.parent).isEqualTo(firstChild)
        assertThat(thirdChild.parent).isEqualTo(secondChild)
    }

}

class FixtureReply {
    companion object {
        fun aReply(
            commentId: Long,
            parent: Reply?,
            userId: Long,
            mentionUserIds: List<Long>,
            content: String,
            children: MutableList<Reply>,
        ) = Reply.create(
            commentId = commentId,
            parent = parent,
            userId = userId,
            mentionUserIds = MentionUserIds(mentionUserIds),
            content = content,
            children = children
        )
    }
}