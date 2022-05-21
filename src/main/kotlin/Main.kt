import problem.input.InputParser
import problem.train.Train
import problem.train.TrainManager

fun main(args: Array<String>) {
    val inputFileLocation = args[0]
    val input = InputParser().parseInput(inputFileLocation)

    val trainA = Train(
        name = input.trainA.name,
        engine = input.trainA.engine,
        bogies = input.trainA.bogies,
    )

    val trainB = Train(
        name = input.trainB.name,
        engine = input.trainB.engine,
        bogies = input.trainB.bogies,
    )

    val trainManager = TrainManager(trainA = trainA, trainB = trainB)

    trainManager.logArrivalAndDeparture()
}
