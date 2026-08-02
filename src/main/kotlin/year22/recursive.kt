package year22

fun main() {
    val a = recursiveAdd(5)
    println(a)

    val testList = listOf(1,5,7,22,16)
    val b = recursiveListCount(testList)
    println(b)
}

private fun recursiveAdd(n: Int): Int =
    if (n == 1) {
        return n
    } else {
        n + recursiveAdd(n - 1)
    }

private tailrec fun recursiveListCount(list: List<Int>): Int =
    if (list.isEmpty()) {
        0
    } else {
        1 + recursiveListCount(list.drop(1))
    }

private tailrec fun sumElementsTailrec(list: List<Int>, acc: Int = 0): Int =
    if (list.isEmpty()) {
        acc
    } else {
        sumElementsTailrec(list.drop(1), acc + list.first())
    }
