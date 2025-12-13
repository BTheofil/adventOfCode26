import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    //day3part1()
    day3part2()
}

private fun day3part1() {
    val banks = mutableListOf<Int>()

    val mock = listOf("82223333323")
    parseFile().forEach { line ->
        for (number in 99 downTo 11) {
            val tensDigit = (number / 10).digitToChar()
            val onesDigit = (number % 10).digitToChar()
            val tensDigitIndex = line.indexOf(tensDigit)
            if (tensDigitIndex != -1) {
                val onesDigitIndex = line.indexOf(startIndex = tensDigitIndex + 1, char = onesDigit)
                if (onesDigitIndex != -1) {
                    println(number)
                    banks.add(number)
                    break
                }
            }
        }
    }

    println(banks.sum())
}

private fun day3part2() {
    val solutions = mutableListOf<Long>()

    parseFile().forEach { line ->
        var banks = ""
        var startIndex = 0
        (1..12).forEach { _ ->
            val (nextIndex, digit) = findNextBigDigit(
                startIndex = startIndex,
                line = line,
                banksLength = banks.length
            )
            startIndex = nextIndex + 1
            banks += digit
        }
        solutions.add(banks.toLong())
    }

    println(solutions)
    println(solutions.sum())
}

private fun findNextBigDigit(
    startIndex: Int,
    line: String,
    banksLength: Int,
): Pair<Int, Char> {
    for (digit in '9' downTo '1') {
        val indexOfDigit = line.indexOf(
            startIndex = startIndex,
            char = digit
        )
        if (indexOfDigit != -1 && (line.length - indexOfDigit) >= (12 - banksLength)) {
            return indexOfDigit to line[indexOfDigit]
        }
    }
    return TODO("Provide the return value")
}

private fun parseFile(): List<String> = Path("src/main/resources/year25/day3.txt")
    .readLines()