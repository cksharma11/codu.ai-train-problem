package list_utils

object ListUtils {
    fun merge(l1: List<String>, l2: List<String>): List<String> {
        return listOf(l1, l2).flatten()
    }
}
