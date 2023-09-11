fun main() {
    val lines = readInput("day9").map { it.toLong() }
    val invalidNumber = findInvalidNumber(25, lines)
    println(invalidNumber)

    val invalidIndex = lines.indexOf(invalidNumber)

    for (i in 0..invalidIndex) {
        for (j in i + 1..invalidIndex) {
            val currentSumList = buildListFromRange(i..j, lines)
            if (currentSumList.sum() == invalidNumber) {
                println(currentSumList)
                println(currentSumList.min() + currentSumList.max())
            }
        }
    }
}

private fun findInvalidNumber(preamble: Int, lines: List<Long>): Long {
    for (i in 0..<(lines.size - preamble)) {
        val currentSumSet = buildSumSetFromRange(i..i + preamble, lines)
        val curr = lines[i + preamble]
        if (curr !in currentSumSet) return curr
    }
    return 0
}

private fun buildListFromRange(range: IntRange, lines: List<Long>): List<Long> =
    buildList { range.forEach { add(lines[it]) } }

private fun buildSumSetFromRange(range: IntRange, lines: List<Long>): Set<Long> =
    buildSet {
        for (i in range) {
            for (j in i + 1..<range.last) {
                add(lines[i] + lines[j])
            }
        }
    }
