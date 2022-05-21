package problem.train

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals


internal class TrainManagerTest {

    private val outContent = ByteArrayOutputStream()
    private val errContent = ByteArrayOutputStream()
    private val originalOut = System.out
    private val originalErr = System.err

    @Before
    fun setUpStreams() {
        System.setOut(PrintStream(outContent))
        System.setErr(PrintStream(errContent))
    }

    @After
    fun restoreStreams() {
        System.setOut(originalOut)
        System.setErr(originalErr)
    }

    @Test
    fun `should log train arrival when when journey not ended`() {
        val trainA = Train(
            name = "TRAIN_A",
            bogies = listOf("NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP", "BLR"),
            engine = listOf("ENGINE")
        )

        val trainB = Train(
            name = "TRAIN_B",
            bogies = listOf("SRR", "MAO", "NJP", "PNE", "PTA"),
            engine = listOf("ENGINE")
        )

        val trainManager = TrainManager(trainA = trainA, trainB = trainB)
        trainManager.logArrivalAndDeparture()
        assertEquals(
            "ARRIVAL TRAIN_A ENGINE NDL NDL GHY NJP NGP\n" +
                    "ARRIVAL TRAIN_B ENGINE NJP PTA\n" +
                    "DEPARTURE TRAIN_AB ENGINE ENGINE GHY NJP NJP PTA NDL NDL NGP".trim(),
            outContent.toString().trim()
        )
    }

    @Test
    fun `should log journey ended when no bogies left from hyd`() {
        val trainA = Train(
            name = "TRAIN_A",
            bogies = listOf("BLR"),
            engine = listOf("ENGINE")
        )

        val trainB = Train(
            name = "TRAIN_B",
            bogies = listOf("TVC"),
            engine = listOf("ENGINE")
        )

        val trainManager = TrainManager(trainA = trainA, trainB = trainB)
        trainManager.logArrivalAndDeparture()
        assertEquals(
            "ARRIVAL TRAIN_A ENGINE \n" +
                    "ARRIVAL TRAIN_B ENGINE \n" +
                    "JOURNEY_ENDED".trim(),
            outContent.toString().trim()
        )
    }
}
