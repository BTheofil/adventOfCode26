package year15

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    //part1()
    part2()
}

private fun part1() {
    var solution = 0

    parseFile().forEach { line ->
        val parts = line.split('x')
        val l = parts[0].toInt()
        val w = parts[1].toInt()
        val h = parts[2].toInt()

        val smallestSide = listOf((l * w), (w * h), (h * l)).min()
        val sum = (2 * l * w + 2 * w * h + 2 * h * l) + smallestSide
        solution += sum
    }

    println(solution)
}

private fun part2() {
    var solution = 0

    parseFile().forEach { line ->
        val parts = line.split('x')
        val l = parts[0].toInt()
        val w = parts[1].toInt()
        val h = parts[2].toInt()

        val dimensions = mutableListOf(l, w, h)
        val highestPerimeter = dimensions.max()
        dimensions.remove(highestPerimeter)

        val ribbon = dimensions[0] + dimensions[0] + dimensions[1] + dimensions[1]
        val bow = l * w * h

        solution += (ribbon + bow)
    }

    println(solution)
}

private fun parseFile(): List<String> = Path("src/main/resources/year15/02.txt").readLines()