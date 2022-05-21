package problem.input

import file_utils.FileUtils
import problem.input.models.InputTrain
import problem.input.models.ParsedInput

class InputParser {

    private fun createInputTrain(trainAInput: List<String>): InputTrain {
        val name = trainAInput[0]
        val engine = trainAInput[1]
        val bogies = trainAInput.subList(2, trainAInput.size)

        return InputTrain(name = name, engine = listOf(engine), bogies = bogies)
    }

    fun parseInput(inputFileLocation: String): ParsedInput {
        val input = FileUtils.readFile(inputFileLocation)

        val trainAInput = input[0].split(" ")
        val trainBInput = input[1].split(" ")

        return ParsedInput(
            trainA = createInputTrain(trainAInput),
            trainB = createInputTrain(trainBInput)
        )
    }
}
