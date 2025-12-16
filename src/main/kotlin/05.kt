import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    //part1()
    part2()
}

private fun part1() {
    val ranges = getRanges()

    val ids = mutableListOf<String>()
    for (line in parseFile().drop(ranges.size + 1)) {
        ids.add(line)
    }

    val freshCount = mutableListOf<String>()
    ids.forEach { id ->
        ranges.forEach { range ->
            if (id.toLong() in range.first.toLong()..range.second.toLong())
                freshCount.add(id)
        }
    }

    println(freshCount.distinct().size)
}

private fun part2() {
    val sortedRanges = getRanges2().sortedBy { it.first }

    var count = 0L
    var lastId = 0L
    for (range in sortedRanges) {
        if (range.first > lastId) {
            count += (range.last - range.first) + 1
            lastId = range.last
        }

        if (range.last > lastId) {
            val more = range.last - lastId
            count += more
            lastId = range.last
        }
    }

    println(count)
}

private fun getRanges2(): List<LongRange> {
    val ranges = mutableListOf<LongRange>()
    for (it in parseFile()) {
        if (it == "") break

        val (start, end) = it.split("-")
        ranges.add(LongRange(start = start.toLong(), endInclusive = end.toLong()))
    }
    return ranges
}

private fun getRanges(): List<Pair<String, String>> {
    val ranges = mutableListOf<Pair<String, String>>()
    for (it in parseFile()) {
        if (it == "") break

        val (start, end) = it.split("-")
        ranges.add(start to end)
    }
    return ranges
}

private fun parseFile(): List<String> =
    Path("src/main/resources/year25/day5.txt")
        .readLines()
