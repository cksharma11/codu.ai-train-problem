package problem.train

import constants.Constants
import list_utils.ListUtils
import problem.train.logger.OutputLogger
import problem.train.model.StationModel
import route.RouteUtils

class TrainManager(
    private val trainA: Train,
    private val trainB: Train
) {
    fun logArrivalAndDeparture() {
        val outputLogger = OutputLogger(trainA = trainA, trainB = trainB, trainAB = createTrainAB())
        outputLogger.log()
    }

    private fun createTrainAB(): Train {
        val joinedBogies = joinBogies(trainA.orderOfArrivalAtHyd(), trainB.orderOfArrivalAtHyd())
        val sortedBogies = sortBogies(joinedBogies)

        return Train(
            name = Constants.TRAIN_AB_NAME,
            engine = listOf(trainA.engine, trainB.engine).flatten(),
            bogies = sortedBogies
        )
    }

    private fun joinBogies(bogie1: List<String>, bogie2: List<String>): List<StationModel> {
        val routeDistanceMap = RouteUtils.createRelativeDistanceMapFromHyd()
        val combinedStationCodes = ListUtils.merge(bogie1, bogie2)

        return combinedStationCodes.map { stationCode ->
            StationModel(
                stationCode = stationCode,
                distanceFromHyd = routeDistanceMap[stationCode]!!
            )
        }
    }

    private fun sortBogies(joinedBogies: List<StationModel>): List<String> {
        return joinedBogies.sortedBy { (_, distance) -> distance }
            .map { (stationCode, _) -> stationCode }.reversed()
    }
}
