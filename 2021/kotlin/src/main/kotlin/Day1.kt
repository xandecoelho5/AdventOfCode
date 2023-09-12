fun main() {
    val depths = readInput("day1").map { it.toInt() }
    println(depths.filterIncreasedMeasures())
    println(depths.measureWindows())
}

private fun List<Int>.measureWindows() = windowed(3) { it.sum() }.filterIncreasedMeasures()

private fun List<Int>.filterIncreasedMeasures() = zipWithNext { a, b -> b - a }.count { it > 0 }
