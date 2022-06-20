package com.tastyday.common.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IdGeneratorTest {
    @Test
    internal fun `ID_생성_테스트`() {
        val createId = IdGenerator.createId()
        assertThat(createId).hasSize(36)
    }
}