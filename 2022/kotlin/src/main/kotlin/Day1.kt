import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

fun main() {
    val sums: List<Int> = readSumsFromFile("src/main/resources/day1.txt")
    printSortedSums(sums)
    printTop3Sum(sums)
}

private fun readSumsFromFile(filename: String): List<Int> {
    val sums: MutableList<Int> = ArrayList()
    BufferedReader(InputStreamReader(FileInputStream(filename))).use { br ->
        var line: String?
        var sum = 0
        while (br.readLine().also { line = it } != null) {
            if (line!!.isNotEmpty()) {
                sum += parseLineAsInteger(line!!)
            } else {
                sums.add(sum)
                sum = 0
            }
        }
        sums.add(sum)
    }
    return sums
}

private fun parseLineAsInteger(line: String): Int {
    return try {
        line.toInt()
    } catch (e: NumberFormatException) {
        0
    }
}

private fun printSortedSums(sums: List<Int>) {
    println("Sorted sums: ${sums.sortedDescending()}")
}

private fun printTop3Sum(sums: List<Int>) {
    val top3Sum = sums.sortedDescending().stream().limit(3).mapToInt { obj: Int -> obj }.sum()
    println("Top 3 sum: $top3Sum")
}
