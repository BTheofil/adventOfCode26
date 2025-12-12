import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val paperPosList = parse()

    val result = paperPosList.count { pos ->
        val neighbours = pos.getPosNeighbours()
        val countExisting = neighbours.count { it in paperPosList }
        countExisting < 4
    }

    println(result)
}

val neighbourOffsets = listOf(
    PaperPos(-1, -1), PaperPos(0, -1), PaperPos(1, -1),
    PaperPos(-1, 0), /*PaperPos(0, 0),*/ PaperPos(1, 0),
    PaperPos(-1, 1), PaperPos(0, 1), PaperPos(1, 1)
)

data class PaperPos(val x: Int, val y: Int) {
    fun getPosNeighbours(): List<PaperPos> =
        neighbourOffsets.map { PaperPos(x + it.x, y + it.y) }
}

private fun parse(): List<PaperPos> = Path("src/main/resources/day4.txt")
    .readLines()
    .flatMapIndexed { y, line ->
        line.mapIndexedNotNull { x, ch -> if (ch == '@') PaperPos(x = x, y = y) else null }
    }