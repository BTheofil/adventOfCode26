package year15

fun main() {
    part1()
    part2()
}

private fun part1() {
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

private fun part2() {
    var increasedCounter = 0
    var allCounter = 0

    readFileLines("08.txt").forEach { line ->
        allCounter += line.count()

        val decoded = line
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")

        increasedCounter += decoded.length + 2
    }

    println(increasedCounter - allCounter)
}