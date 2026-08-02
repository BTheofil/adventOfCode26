package year22

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun readFileLines(fileName: String): List<String> = Path("src/main/resources/year22/$fileName").readLines()