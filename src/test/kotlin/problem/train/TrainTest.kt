package problem.train

import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class TrainTest {

    lateinit var train: Train

    @Before
    fun setUp() {
        train = Train(
            name = "TRAIN_A",
            bogies = listOf("NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP", "BLR"),
            engine = listOf("ENGINE")
        )
    }

    @Test
    fun `should return order of arrival when bogies are left in train`() {
        assertEquals(listOf("NDL", "NDL", "GHY", "NJP", "NGP"), train.orderOfArrivalAtHyd())
    }

    @Test
    fun `should return empty bogie when no bogies left till hyd`() {
        val smallTrain = Train(
            name = "TRAIN_A",
            bogies = listOf("BLR"),
            engine = listOf("ENGINE")
        )
        assertEquals(listOf<String>(), smallTrain.orderOfArrivalAtHyd())
    }

    @Test
    fun `should return order of departure from hyd`() {
        assertEquals(listOf("NDL", "NDL", "GHY", "NJP", "NGP"), train.orderOfDepartureFromHyd())
    }

    @Test
    fun `should remove hyd from order of departure`() {
        val trainHavingHydStation = Train(
            name = "TRAIN_A",
            bogies = listOf("HYB", "NGP", "BLR"),
            engine = listOf("ENGINE")
        )
        assertEquals(listOf("NGP"), trainHavingHydStation.orderOfDepartureFromHyd())
    }

    @Test
    fun `should return false if no bogies left in train`() {
        assertFalse { train.isJourneyCompleted() }
    }

    @Test
    fun `should return true if no bogies left in train`() {
        val emptyBogieTrain = Train(
            name = "TRAIN_A",
            bogies = listOf(),
            engine = listOf("ENGINE")
        )
        assertTrue { emptyBogieTrain.isJourneyCompleted() }
    }
}
