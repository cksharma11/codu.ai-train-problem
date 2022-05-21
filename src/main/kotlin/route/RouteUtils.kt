package route

import constants.Constants
import file_utils.FileUtils

object RouteUtils {
    fun createRelativeDistanceMapFromHyd(): HashMap<String, Double> {
        val inputARoute = FileUtils.readFile("src/main/resources/input/trainARoute.txt")
        val trainARouteMap = parseRoute(inputARoute)
        val inputBRoute = FileUtils.readFile("src/main/resources/input/trainBRoute.txt")
        val trainBRouteMap = parseRoute(inputBRoute)

        val routeDistanceMap = HashMap<String, Double>()
        for ((stationCode, distance) in trainARouteMap) {
            routeDistanceMap[stationCode] = distance - trainARouteMap[Constants.HYDERABAD_STATION_CODE]!!
        }
        for ((stationCode, distance) in trainBRouteMap) {
            routeDistanceMap[stationCode] = distance - trainBRouteMap[Constants.HYDERABAD_STATION_CODE]!!
        }
        return routeDistanceMap
    }

    private fun parseRoute(inputRoute: List<String>): HashMap<String, Double> {
        val route = HashMap<String, Double>()
        for (inputStation in inputRoute) {
            val codeAndDistance = inputStation.split(" ")
            val stationCode = codeAndDistance[0]
            val distance = codeAndDistance[1].toDouble()
            route[stationCode] = distance
        }
        return route
    }
}
