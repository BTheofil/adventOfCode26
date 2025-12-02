import kotlin.collections.forEach
import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    //println(-1000 / 100)
    //part1()
    part2()
}

private fun part1() {
    var zeroCount = 0
    var currentPoint = 50

    parseInput().forEach { (dir, amount) ->
        currentPoint = when (dir) {
            'L' -> currentPoint - amount
            'R' -> currentPoint + amount
            else -> currentPoint
        }

        currentPoint = ((currentPoint % 100) + 100) % 100

        if (currentPoint == 0) zeroCount++
    }

    println(zeroCount)
}

private fun part2() {
    var zeroCount = 0
    var currentPoint = 50

    val mock = listOf(Pair('L', 1000))
    parseInput().forEach { (dir, amount) ->
        currentPoint = when (dir) {
            'L' -> currentPoint - amount
            'R' -> currentPoint + amount
            else -> currentPoint
        }

        if (currentPoint / 100 > 0) zeroCount += currentPoint / 100
        if (currentPoint / 100 < 0) zeroCount += (currentPoint / 100) * -1
        currentPoint = ((currentPoint % 100) + 100) % 100

        if (currentPoint == 0) zeroCount++
    }

    println(zeroCount)
}

fun parseInput(): List<Pair<Char, Int>> =
    Path("src/main/resources/day1.txt").readLines().map {
        val direction = it.first()
        val number = it.drop(1).toInt()
        direction to number
    }