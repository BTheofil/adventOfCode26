import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    //day2part1()
    day2part2()
}

private fun day2part1() {
    val invalidIds = mutableListOf<Long>()
    //val mock = listOf(446446,38593859)
    parseInput2().forEach { (start, end) ->
        for (id in start..end) {
            val idLength = id.toString().length
            if (idLength % 2 == 0) {
                var matched = 0
                for (i in 0..<idLength / 2) {
                    if (id.toString()[i] == id.toString()[(idLength / 2) + i]) {
                        matched++
                    }
                    if (matched == idLength / 2) {
                        invalidIds.add(id)
                    }
                }
            }
        }
    }

    println(invalidIds.sum())
}

private fun day2part2() {
    val invalidIds = mutableListOf<Long>()

    parseInput2().forEach { (start, end) ->
        for (id in start..end) {
            val maxChunkLength = id.toString().length / 2
            for (chunk in 1..maxChunkLength) {
                val chunks = id.toString().chunked(chunk)
                val isInvalidId = chunks.all { it == chunks.first() }

                if (isInvalidId) {
                    invalidIds.add(id)
                    break
                }
            }
        }
    }

    println(invalidIds.sum())
}

private fun parseInput2(): List<Pair<Long, Long>> =
    Path("src/main/resources/year25/day2.txt")
        .readLines()
        .flatMap { line ->
            line.split(",").map { range ->
                val (start, end) = range.split("-")
                start.toLong() to end.toLong()
            }
        }