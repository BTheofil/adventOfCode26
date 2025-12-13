import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {

    val ranges = mutableListOf<Pair<String, String>>()
    for (it in parseFile()) {
        if (it == "") break

        val (start, end) = it.split("-")
        ranges.add(start to end)
    }

    val ids = mutableListOf<String>()
    for (line in parseFile().drop(ranges.size + 1)) {
        ids.add(line)
    }

}

private fun parseFile(): List<String> =
    Path("src/main/resources/year25/day5.txt")
        .readLines()
