import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    //part1()
    part2()
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

private fun part2() {
    val lines = Path("src/main/resources/year25/day6.txt")
        .readLines()

    val operatorLine = lines.last().trim().split(Regex("\\s+"))
    val numberLines = lines.dropLast(1)

    val problems = mutableListOf<IntRange>()
    var lineIndex = 0

    while (lineIndex < lines.first().length) {

        val start = lineIndex

        while (lineIndex < lines.first().length && !numberLines.all { it[lineIndex] == ' ' }) lineIndex++

        val end = lineIndex - 1
        problems.add(start..end)

        while (lineIndex < lines.first().length && numberLines.all { it[lineIndex] == ' ' }) lineIndex++
    }


    var total = 0L
    for ((i, problem) in problems.withIndex()) {

        val problemNumbers = mutableListOf<Int>()

        for (currentCharacterIndex in problem) {

            val builtNumber = buildString {
                numberLines.indices.forEach { horizontalIndex ->
                    val char = numberLines[horizontalIndex][currentCharacterIndex]
                    if (char.isDigit()) this.append(char)
                }
            }

            problemNumbers.add(builtNumber.toInt())
        }

        when (operatorLine[i]) {
            "*" -> {
                val result = problemNumbers.fold(1L) { acc, n -> acc * n }
                total += result
            }

            "+" -> {
                val result = problemNumbers.sum()
                total += result
            }
        }
    }

    println(total)
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