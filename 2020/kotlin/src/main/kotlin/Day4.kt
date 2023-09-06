fun main() {
    val lines = readInput("day4")
    val passports = getPassportsFromLines(lines).filter { it.size == 8 || ((it - "cid").size == 7) }
    part1(passports).println()
    part2(passports).println()
}

fun part1(passports: List<Map<String, String>>) = passports.size

fun part2(passports: List<Map<String, String>>): Int {
    fun numValidator(value: String, range: IntRange) = value.length == 4 && value.toInt() in range

    fun hgtValidator(value: String): Boolean {
        if ("cm" in value) return value.split("cm")[0].toInt() in 150..193
        if ("in" in value) return value.split("in")[0].toInt() in 59..76
        return false
    }

    fun hclValidator(value: String): Boolean {
        if (value[0] != '#' || value.length != 7) return false
        return value.substring(1)
            .filter { it in '0'..'9' || it in 'a'..'f' }.length == 6
    }

    fun validateEntry(key: String, value: String): Boolean {
        return when (key) {
            "byr" -> numValidator(value, 1920..2002)
            "iyr" -> numValidator(value, 2010..2020)
            "eyr" -> numValidator(value, 2020..2030)
            "hgt" -> hgtValidator(value)
            "hcl" -> hclValidator(value)
            "ecl" -> value in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
            "pid" -> value.filter { it.isDigit() }.length == 9
            else -> true
        }
    }

    return passports.filter { p -> p.all { validateEntry(it.key, it.value) } }.size
}

fun getPassportsFromLines(lines: List<String>): List<Map<String, String>> {
    return StringBuilder().apply {
        for (line in lines) if (line.trim().isEmpty()) append("<>") else append(line).append(" ")
    }.split("<>").map {
        it.trim().split(" ").associate { field ->
            val (key, value) = field.split(":")
            key to value
        }
    }
}
