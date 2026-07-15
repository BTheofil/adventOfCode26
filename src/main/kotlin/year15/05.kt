package year15

fun main() {
    //part1()
    part2()
}

private fun part1() {
    val lines = readFileLines("05.txt")

    var niceCount = 0
    val vowels = "aeiou"
    for (line in lines) {
        var vowelsCount = 0
        var lastChar: Char? = null
        var atLeastDoubleCharInRow = false
        var containsBannedString = false
        for (char in line) {
            if ("ab" in line ||
                "cd" in line ||
                "pq" in line ||
                "xy" in line
            ) {
                containsBannedString = true
                break
            }

            if (char in vowels) {
                vowelsCount++
            }

            if (lastChar == null) {
                lastChar = char
                continue
            } else if (lastChar == char) {
                atLeastDoubleCharInRow = true
            } else {
                lastChar = char
            }
        }
        if (vowelsCount >= 3 && atLeastDoubleCharInRow && !containsBannedString) {
            niceCount++
        }
    }

    println(niceCount)

}

private fun part2() {
    val lines = readFileLines("05.txt")

    var counter = 0
    lines.forEach { line ->
        val pairs = mutableListOf<String>()
        var is1ruleCompleted = false
        var is2ruleCompleted = false
        for (index in 1..<line.length) {

            //1.rule
            val currentPair = (line[index - 1]).toString() + line[index].toString()

            if (currentPair !in pairs) {
                pairs.add(currentPair)
            } else {
                if (currentPair != pairs.last()) {
                    is1ruleCompleted = true
                }
            }

            //2.rule
            if (index >= 2) {
                if (line[index - 2] == line[index]) {
                    is2ruleCompleted = true
                }
            }
        }

        if (is1ruleCompleted && is2ruleCompleted) {
            counter++
        }
    }

    println(counter)
}