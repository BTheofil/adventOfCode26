import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    parse()
}

private fun parse(): List<List<Int>> = Path("src/main/resources/day4.txt")
    .readLines()
    .map {
        val temp = mutableListOf<Int>()
        it.forEach { c ->
            when (c) {
                '.' -> temp.add(0)
                '@' -> temp.add(1)
            }
        }
        temp
    }