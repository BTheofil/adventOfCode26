package year15

import kotlin.io.path.Path
import kotlin.io.path.readLines

data class Coordinate(val x: Int, val y: Int)

fun main() {
    //p1()
    p2()
}

fun p1() {
    var position = Coordinate(0, 0)
    val locations = mutableListOf(Coordinate(0, 0))

    parseFile().first().forEach { ch ->
        when (ch) {
            '^' -> position = position.copy(y = position.y + 1)
            'v' -> position = position.copy(y = position.y - 1)
            '>' -> position = position.copy(x = position.x + 1)
            '<' -> position = position.copy(x = position.x - 1)
        }
        locations.add(position)
    }

    println(locations.toSet().size)
}

fun p2() {
    val santaLocations = mutableListOf(Coordinate(0, 0))
    val roboSantaLocations = mutableListOf(Coordinate(0, 0))

    var santa = Coordinate(0,0)
    var roboSanta = Coordinate(0,0)
    parseFile().first().forEachIndexed { index, ch ->
        if (index % 2 == 0) {
            when (ch) {
                '^' -> santa = santa.copy(y = santa.y + 1)
                'v' -> santa = santa.copy(y = santa.y - 1)
                '>' -> santa = santa.copy(x = santa.x + 1)
                '<' -> santa = santa.copy(x = santa.x - 1)
            }
            santaLocations.add(santa)
        } else {
            when (ch) {
                '^' -> roboSanta = roboSanta.copy(y = roboSanta.y + 1)
                'v' -> roboSanta = roboSanta.copy(y = roboSanta.y - 1)
                '>' -> roboSanta = roboSanta.copy(x = roboSanta.x + 1)
                '<' -> roboSanta = roboSanta.copy(x = roboSanta.x - 1)
            }
            roboSantaLocations.add(roboSanta)
        }
    }

    println((santaLocations.toSet() + roboSantaLocations.toSet()).size)
}

private fun parseFile(): List<String> = Path("src/main/resources/year15/03.txt").readLines()