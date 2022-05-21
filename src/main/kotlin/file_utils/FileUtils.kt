package file_utils

import java.io.File

object FileUtils {
    fun readFile(filepath: String): List<String> {
        return File(filepath).readLines()
    }
}
