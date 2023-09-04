fun main() {
    val line = readInput("day6")[0]
    println(getMarker(line, 4))
    println(getMarker(line, 14))
}

private fun getMarker(line: String, distinctNumbers: Int): Int {
    for (i in 0..<line.length - distinctNumbers - 1) {
        val size = line.substring(i, i + distinctNumbers)
            .split("")
            .filter { it != "" }
            .toHashSet().size
        if (size == distinctNumbers) return i + distinctNumbers
    }
    return 0
}
