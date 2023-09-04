import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer

fun main() {
    val totalItemPriority = AtomicInteger(0)
    calculatePriorityFromFile("src/main/resources/day3.txt") { line: String ->
        val half = line.length / 2
        val firstCompartment = line.substring(0, half)
        val secondCompartment = line.substring(half)
        val item: Char = findCommonItem(firstCompartment, secondCompartment)
        totalItemPriority.getAndAdd(calculateCharValue(item))
    }
    println(totalItemPriority.get())

    val totalBadgePriority = AtomicInteger(0)
    val lines: MutableList<String> = ArrayList()
    calculatePriorityFromFile("src/main/resources/day3.txt") { line: String ->
        lines.add(line)
        if (lines.size == 3) {
            lines.sortedByDescending { it.length }
            val badge: Char = findCommonBadge(lines[0], lines[1], lines[2])
            totalBadgePriority.getAndAdd(calculateCharValue(badge))
            lines.clear()
        }
    }
    println(totalBadgePriority.get())
}

private fun calculatePriorityFromFile(filename: String, lineConsumer: Consumer<String>) {
    BufferedReader(InputStreamReader(FileInputStream(filename))).use { br ->
        var line: String?
        while (br.readLine().also { line = it } != null) {
            lineConsumer.accept(line!!)
        }
    }
}

private fun findCommonItem(firstCompartment: String, secondCompartment: String): Char {
    for (element in firstCompartment) {
        if (secondCompartment.indexOf(element) > -1) return element
    }
    return '-'
}

private fun findCommonBadge(
    firstRucksack: String,
    secondRucksack: String,
    thirdRucksack: String
): Char {
    for (element in firstRucksack) {
        if (secondRucksack.indexOf(element) > -1 && thirdRucksack.indexOf(element) > -1) return element
    }
    return '-'
}

private fun calculateCharValue(c: Char): Int {
    return if (c in 'A'..'Z') c.code - 38 else c.code - 96
}
