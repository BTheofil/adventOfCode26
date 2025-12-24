import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    part1()
}

private fun part1() {
    val data = parse()

    var sum = 0L
    for ((index, operator) in data.operators.withIndex()) {
        val numbers = mutableListOf<Int>()
        for (number in 0..<4) {
            val currentNumber = data.numbers[number][index]
            numbers.add(currentNumber)
        }
        when (operator) {
            "+" -> {
                sum += numbers.sum()
            }

            "*" -> {
                var temp = 1L
                numbers.forEach { temp *= it }
                sum += temp
            }
        }
    }

    println(sum)
}

private data class Data(
    val numbers: List<List<Int>>,
    val operators: List<String>
)

private fun parse(): Data {
    val numberRows = mutableListOf<List<Int>>()
    var operators: List<String> = emptyList()

    Path("src/main/resources/year25/day6.txt")
        .readLines()
        .forEach { line ->
            val formattedLine = line.trim().split(Regex("\\s+"))

            if (formattedLine.first().toIntOrNull() != null) {
                numberRows.add(formattedLine.map { it.toInt() })
            } else {
                operators = formattedLine
            }
        }

    return Data(numberRows, operators)
}