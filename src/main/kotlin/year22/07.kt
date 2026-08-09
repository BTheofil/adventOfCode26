package year22

data class Directory(
    val name: String,
    val parent: Directory? = null
) {
    val children = mutableMapOf<String, Directory>()
    var totalSize = 0L

    fun getDirectorySize(): Long {
        return totalSize + children.values.sumOf { it.getDirectorySize() }
    }

    fun getAllDirectories(): List<Directory> {
        return listOf(this) + children.values.flatMap { it.getAllDirectories() }
    }
}

fun main() {
    val root = Directory("/")
    var currentDirectory = root

    readFileLines("07.txt").forEach { line ->
        when {
            line == "$ cd /" -> currentDirectory = root
            line == "$ cd .." -> currentDirectory = currentDirectory.parent ?: root
            line.startsWith("$ cd ") -> {
                val parts = line.split(" ")
                currentDirectory = currentDirectory.children.getOrPut(
                    key = parts[2],
                    defaultValue = {
                        Directory(name = parts[2], parent = currentDirectory)
                    }
                )
            }

            line == "$ ls" -> {}
            else -> {
                val parts = line.split(" ")
                if (parts[0].toLongOrNull() != null) {
                    currentDirectory.totalSize += parts[0].toLong()
                }
            }
        }
    }

    val number = root.getAllDirectories()
        .map { it.getDirectorySize() }
        .filter { it <= 100_000 }
        .sum()

    println(number) //part1

    val totalSpace = 70_000_000L
    val requiredUnused = 30_000_000L
    val currentUnused = totalSpace - root.getDirectorySize()
    val neededToFree = requiredUnused - currentUnused

    val number2 = root.getAllDirectories()
        .map { it.getDirectorySize() }
        .filter { it >= neededToFree }
        .minOrNull()

    println(number2)
}