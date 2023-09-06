fun main() {
    val policies = readInput("day2").map(Policy.Companion::parse)
    policies.part1().println()
    policies.part2().println()
}

fun List<Policy>.part1() = count { it.isValidPart1() }

fun List<Policy>.part2() = count { it.isValidPart2() }

data class Policy(val range: IntRange, val letter: Char, val password: String) {
    fun isValidPart1() = password.count { it == letter } in range
    fun isValidPart2() =
        (password[range.first - 1] == letter) xor (password[range.last - 1] == letter)

    companion object {
        fun parse(line: String): Policy = Policy(
            password = line.substringAfter(": "),
            letter = line.substringAfter(" ").substringBefore(":").single(),
            range = line.substringBefore(" ").let {
                val (start, end) = it.split("-")
                start.toInt()..end.toInt()
            }
        )
    }
}
