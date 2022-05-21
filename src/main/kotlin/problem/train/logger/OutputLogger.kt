package problem.train.logger

import constants.Constants
import problem.train.Train

class OutputLogger(
    val trainA: Train,
    val trainB: Train,
    private val trainAB: Train,
) {
    fun log() {
        logArrival(trainA)
        logArrival(trainB)
        logDeparture(trainAB)
    }

    private fun logArrival(train: Train) {
        println("${Constants.ARRIVAL} ${train.name} ${listToString(train.engine)} ${listToString(train.orderOfArrivalAtHyd())}")
    }

    private fun logDeparture(train: Train) {
        if (train.isJourneyCompleted()) {
            println(Constants.JOURNEY_ENDED)
        } else {
            println("${Constants.DEPARTURE} ${train.name} ${listToString(train.engine)} ${listToString(train.orderOfDepartureFromHyd())}")
        }
    }

    private fun listToString(list: List<String>): String {
        var string = ""
        for (i in list.indices) {
            if (i == 0) {
                string += list[i]
            } else {
                string += " ${list[i]}"
            }
        }
        return string
    }
}
