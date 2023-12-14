private fun main() {
    val lines = readInput("day1")
    println(lines.part1())
    println(lines.part2())
}

private val words = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

private fun calibrationValue(row: String) =
    "${row.first { it.isDigit() }}${row.last { it.isDigit() }}".toInt()

private fun List<String>.part1() = sumOf { row -> calibrationValue(row) }

private fun String.possibleWordsAt(start: Int): List<String> =
    (3..5).map { len ->
        val end = (start + len).coerceAtMost(length)
        substring(start, end)
    }

private fun List<String>.part2() = sumOf { row ->
    calibrationValue(
        row.mapIndexedNotNull { index, c ->
            if (c.isDigit()) c
            else row.possibleWordsAt(index).firstNotNullOfOrNull { candidate -> words[candidate] }
        }.joinToString()
    )
}
