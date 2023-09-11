import kotlin.math.ceil

fun main() {
    val lines = readInput("day5")

    val seats = mutableListOf<Int>()
    for (line in lines) {
        val row = findSeat(line.substring(0, 7), 0..127)
        val column = findSeat(line.drop(7), 0..7)
        seats.add(row * 8 + column)
    }
    println(seats.max())
    println(findMySeat(seats))
}

fun findMySeat(seats: MutableList<Int>): Int {
    seats.sort()
    for (i in seats.first()..seats.last()) {
        if (!seats.contains(i)) return i
    }
    return -1
}

fun findSeat(space: String, wholeRange: IntRange): Int {
    fun find(index: Int, range: IntRange): Int {
        val letter = space[index]

        val mid = ceil(((range.last - range.first) / 2.0)).toInt()
        if (mid == 1) {
            return if (letter in charArrayOf('F', 'L')) range.first else range.last
        }
        return find(
            index + 1, when (letter) {
                'F', 'L' -> range.first..(range.last - mid)
                'B', 'R' -> (range.first + mid)..range.last
                else -> IntRange(0, 1)
            }
        )
    }
    return find(0, wholeRange)
}
