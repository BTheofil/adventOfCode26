package year15

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    var currentFloor = 0
    var firstBasementInx = -1

    parseFile().forEach { line ->
        line.forEachIndexed { idx, floor ->
            if (floor == '(') currentFloor++
            else currentFloor--

            if (currentFloor < 0 && firstBasementInx == -1) firstBasementInx = idx + 1
        }
    }

    println(currentFloor)
    println("first basement index: $firstBasementInx")
}

private fun parseFile(): List<String> = Path("src/main/resources/year15/d1.txt")
    .readLines()