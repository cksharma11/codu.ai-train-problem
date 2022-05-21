package problem.train

import route.RouteUtils

class Train(
    val name: String,
    val engine: List<String>,
    val bogies: List<String>,
) {
    private val routeDistanceMap = RouteUtils.createRelativeDistanceMapFromHyd()

    fun orderOfArrivalAtHyd(): List<String> {
        return bogies.filter { routeDistanceMap[it]!! >= 0 }
    }

    fun orderOfDepartureFromHyd(): List<String> {
        return bogies.filter { routeDistanceMap[it]!! > 0 }
    }

    fun isJourneyCompleted(): Boolean {
        return bogies.isEmpty()
    }
}
