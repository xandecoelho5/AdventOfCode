fun main() {
    val numbers = readInput("day1").map { it.toInt() }
    part1(numbers).println()
    part2(numbers).println()
}

private fun part1(numbers: List<Int>): Int {
    fun findPair(): Pair<Int, Int> {
        for (i in numbers.indices) {
            for (j in numbers.indices) {
                if (i != j && ((numbers[i] + numbers[j]) == 2020)) {
                    return Pair(numbers[i], numbers[j])
                }
            }
        }
        return Pair(0, 0)
    }

    val pair = findPair()
    return pair.first * pair.second
}

private fun part2(numbers: List<Int>): Int {
    fun findTriple(): Triple<Int, Int, Int> {
        for (i in numbers.indices) {
            for (j in numbers.indices) {
                for (k in numbers.indices) {
                    if (i != j && j != k && ((numbers[i] + numbers[j] + numbers[k]) == 2020)) {
                        return Triple(numbers[i], numbers[j], numbers[k])
                    }
                }
            }
        }
        return Triple(0, 0, 0)
    }

    val triple = findTriple()
    return triple.first * triple.second * triple.third
}
