package year15

fun main() {
    var stringCounter = 0
    var allCounter = 0

    readFileLines("08.txt").forEach { line ->
        allCounter += line.count()

        val decoded = line
            .replace("\\\\", "A")
            .replace("\\\"", "A")
            .replace(Regex("""\\x[0-9a-fA-F]{2}"""), "X")

        stringCounter += decoded.length - 2
    }

    println(allCounter - stringCounter)
}