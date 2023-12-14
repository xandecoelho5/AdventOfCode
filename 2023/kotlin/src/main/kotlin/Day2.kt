import kotlin.math.max

private fun main() {
    val lines = readInput("day2")
    println(lines.part1())
    println(lines.part2())
}

private fun List<String>.part1() = mapIndexed { index, row ->
    if (row.extractSets()
            .any { it.extractCubes().checkCubesConfiguration() != null }
    ) 0 else index + 1
}.sum()

private fun List<String>.part2(): Int {
    return sumOf { row ->
        val fewestCubes = row.extractSets().fold(Triple(1, 1, 1)) { prev, set ->
            val extractedCubes = set.extractCubes()
            val (first, second, third) = prev
            val red = max(first, extractedCubes["red"] ?: 1)
            val green = max(second, extractedCubes["green"] ?: 1)
            val blue = max(third, extractedCubes["blue"] ?: 1)
            Triple(red, green, blue)
        }
        fewestCubes.first * fewestCubes.second * fewestCubes.third
    }
}

private val maxBagValues = mapOf("red" to 12, "green" to 13, "blue" to 14)

private fun Map<String, Int>.checkCubesConfiguration(): Int? =
    entries.firstOrNull { it.value > (maxBagValues[it.key] ?: Int.MAX_VALUE) }?.value

private fun String.extractSets(): List<String> = substringAfter(":").split(";")

private fun String.extractCubes(): Map<String, Int> =
    split(",").associate {
        val values = it.trim().split(" ")
        values[1] to values[0].toInt()
    }

