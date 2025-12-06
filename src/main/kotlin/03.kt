import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    day3part1()
}

private fun day3part1() {
    val banks = mutableListOf<Int>()

    parseFile().forEach { line ->
        val searchString = line.dropLast(1)
        (9 downTo 1).forEach { s ->
            val c = s.toString()[0]
            val index = searchString.indexOf(c)
            if (index != -1) {
                val firstDigit = line[index].toString()
                val secondDigit = line.drop(index + 1).max().toString()
                banks.add("$firstDigit$secondDigit".toInt())
            }
        }
    }

    println(banks.sum())
}

private fun parseFile(): List<String> = Path("src/main/resources/day3.txt")
    .readLines()