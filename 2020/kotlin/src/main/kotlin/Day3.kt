fun main() {
    val field = readInput("day3")
    part1(field).println()
    part2(field).println()
}

fun part1(lines: List<String>) = lines.countSlopes(3)

fun part2(lines: List<String>): Int {
    return lines.countSlopes(1) *
            lines.countSlopes(3) *
            lines.countSlopes(5) *
            lines.countSlopes(7) *
            lines.countSlopes(1, 2)
}

fun List<String>.countSlopes(right: Int, down: Int = 1): Int {
    var j = 0
    var count = 0
    for (i in indices step down) {
        val element = this[i]
        if (element[j] == '#') count++
        j += right
        if (j >= element.length) j -= element.length
    }
    return count
}
