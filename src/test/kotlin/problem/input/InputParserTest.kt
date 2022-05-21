package problem.input

import file_utils.FileUtils
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockkObject
import org.junit.After
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import problem.input.models.InputTrain
import problem.input.models.ParsedInput

internal class InputParserTest {

    @Test
    fun `should parse input file`() {
        mockkObject(FileUtils)
        every { FileUtils.readFile(any()) } returns listOf(
            "TRAIN_A ENGINE NDL NDL",
            "TRAIN_B ENGINE NJP GHY"
        )

        val expected = ParsedInput(
            trainA = InputTrain(name = "TRAIN_A", engine = listOf("ENGINE"), bogies = listOf("NDL", "NDL")),
            trainB = InputTrain(name = "TRAIN_B", engine = listOf("ENGINE"), bogies = listOf("NJP", "GHY"))
        )
        assertEquals(
            expected, InputParser().parseInput("/test/input.txt")
        )
    }

    @After
    fun tearDown() {
        clearMocks(FileUtils)
    }
}
