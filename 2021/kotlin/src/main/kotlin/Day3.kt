fun main() {
    val input = readInput("day3")
    println("P1 Result: " + part1(input))
    println("P2 Result: " + part2(input))
}

private enum class RatingType {
    OXYGEN,
    CO2
}

private fun part2(input: List<String>): Int {
    fun rating(type: RatingType): String {
        val columns = input[0].indices
        var candidates = input
        for (column in columns) {
            val (zeroes, ones) = candidates.countBitsInColumn(column)
            val mostCommon = if (zeroes > ones) '0' else '1'
            candidates = candidates.filter {
                when (type) {
                    RatingType.OXYGEN -> it[column] == mostCommon
                    RatingType.CO2 -> it[column] != mostCommon
                }
            }
            if (candidates.size == 1) break
        }
        return candidates.single()
    }
    return rating(RatingType.OXYGEN).toInt(2) * rating(RatingType.CO2).toInt(2)
}

private fun part1(input: List<String>): Int {
    val gammaRate = input[0].indices
        .map { input.countBitsInColumn(it) }
        .joinToString("") { (zeroes, ones) ->
            if (zeroes > ones) "0" else "1"
        }
    val epsilonRate = gammaRate.invertBinaryString()
    return gammaRate.toInt(2) * epsilonRate.toInt(2)
}

private fun String.invertBinaryString() = this
    .asIterable()
    .joinToString("") { if (it == '0') "1" else "0" }

private fun List<String>.countBitsInColumn(column: Int): BitCount {
    var zeroes = 0
    var ones = 0
    for (line in this) {
        if (line[column] == '0') zeroes++ else ones++
    }
    return BitCount(zeroes, ones)
}

data class BitCount(val zeroes: Int, val ones: Int)
