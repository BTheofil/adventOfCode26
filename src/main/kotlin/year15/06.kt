package year15

import kotlin.collections.forEach

fun main() {
    part1()
}

private fun part1() {
    val grid = Array(1000) { Array(1000) { false } }

    readFileLines("06.txt").forEach { line ->
        val parts = line.split(" ")
        when {
            parts[1] == "off" -> {
                val (x1, y1) = parts[2].split(",")
                val (x2, y2) = parts[4].split(",")

                for (x in x1.toInt()..x2.toInt()) {
                    for (y in y1.toInt()..y2.toInt()) {
                        grid[x][y] = false
                    }
                }
            }

            parts[1] == "on" -> {
                val (x1, y1) = parts[2].split(",")
                val (x2, y2) = parts[4].split(",")

                for (x in x1.toInt()..x2.toInt()) {
                    for (y in y1.toInt()..y2.toInt()) {
                        grid[x][y] = true
                    }
                }
            }

            else -> {
                val (x1, y1) = parts[1].split(",")
                val (x2, y2) = parts[3].split(",")

                for (x in x1.toInt()..x2.toInt()) {
                    for (y in y1.toInt()..y2.toInt()) {
                        grid[x][y] = !grid[x][y]
                    }
                }
            }
        }
    }

    var count = 0
    for (x in 0..999) {
        for (y in 0..999) {
            if (grid[x][y]) {
                count++
            }
        }
    }

    println(count)
}