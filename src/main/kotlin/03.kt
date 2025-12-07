import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    day3part1()
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

private fun parseFile(): List<String> = Path("src/main/resources/day3.txt")
    .readLines()