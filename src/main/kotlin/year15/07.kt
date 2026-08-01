package year15

private val instructions = HashMap<String, List<String>>()
val cache = HashMap<String, Int>()

fun main() {
    readFileLines("07.txt").forEach { line ->
        val parts = line.split(" -> ")
        instructions[parts[1]] = parts[0].split(" ")

    }

    println(getSignal("a"))
}

private fun getSignal(wire: String): Int {

    wire.toIntOrNull()?.let { return it }

    if (cache.containsKey(wire)) return cache[wire]!!

    val expression = instructions[wire] ?: error("Not known wire")

    val result = when (expression.size) {
        1 -> getSignal(expression[0])
        2 -> getSignal(expression[1]).inv() and 0xFFFF
        else -> {
            val left = getSignal(expression[0])
            val operation = expression[1]
            val right = getSignal(expression[2])

            val solved = when (operation) {
                "AND" -> left.and(right)
                "OR" -> left.or(right)
                "LSHIFT" -> left.shl(right)
                else -> left.shr(right)
            }

            solved
        }
    }

    cache[wire] = result
    return result
}