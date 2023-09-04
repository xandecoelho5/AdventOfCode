import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

fun main() {
    getQuantityOfOverlappedPairs()
}

private fun getQuantityOfOverlappedPairs() {
    var countAllOverlapping = 0
    var countRangesOverlapping = 0
    BufferedReader(InputStreamReader(FileInputStream("src/main/resources/day4.txt"))).use { br ->
        var line: String?
        while (br.readLine().also { line = it } != null) {
            val sections =
                line!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            val firstSectionRange: Set<Int> = getSetRangefromSection(sections[0])
            val secondSectionRange: Set<Int> = getSetRangefromSection(sections[1])
            if (allSectionOverlaps(firstSectionRange, secondSectionRange)) {
                countAllOverlapping++
            }
            if (rangeSectionOverlaps(firstSectionRange, secondSectionRange)) {
                countRangesOverlapping++
            }
        }
    }
    println(countAllOverlapping)
    println(countRangesOverlapping)
}

private fun getSetRangefromSection(section: String): Set<Int> {
    val numbers = section.split("-".toRegex()).dropLastWhile { it.isEmpty() }
        .toTypedArray()
    val start = numbers[0].toInt()
    val end = numbers[1].toInt()
    val result: MutableSet<Int> = HashSet()
    for (i in start..end) {
        result.add(i)
    }
    return result
}

private fun allSectionOverlaps(first: Set<Int>, second: Set<Int>): Boolean {
    return first.containsAll(second) || second.containsAll(first)
}

private fun rangeSectionOverlaps(first: Set<Int>, second: Set<Int>): Boolean {
    for (value in first) {
        if (second.contains(value)) return true
    }
    return false
}
