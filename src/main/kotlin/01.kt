import kotlin.collections.forEach
import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
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

    parseInput().forEach { (dir, amount) ->
        repeat(amount) {
            if (dir == 'R')
                currentPoint += 1
            else
                currentPoint -= 1
            when (currentPoint) {
                100 -> {
                    currentPoint = 0
                    zeroCount++
                }
                -1 -> {
                    currentPoint = 99
                }
                0 -> zeroCount++
            }
        }
    }

    println(zeroCount)
}

fun parseInput(): List<Pair<Char, Int>> =
    Path("src/main/resources/day1.txt").readLines().map {
        val direction = it.first()
        val number = it.drop(1).toInt()
        direction to number
    }