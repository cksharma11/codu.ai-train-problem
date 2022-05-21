package list_utils

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class ListUtilsTest {

    @Test
    fun `should merge to list of string`() {
        val l1 = listOf("A", "B")
        val l2 = listOf("C", "D")
        val expected = listOf("A", "B", "C", "D")

        assertEquals(expected, ListUtils.merge(l1, l2))
    }

    @Test
    fun `should work on empty list`() {
        val l1 = listOf<String>()
        val l2 = listOf<String>()
        val expected = listOf<String>()

        assertEquals(expected, ListUtils.merge(l1, l2))
    }
}
