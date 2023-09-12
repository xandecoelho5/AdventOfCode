fun main() {
    val input = readInput("day4")

    val drawnNumbers = input[0].split(",").map { it.toInt() }
    val bingoBoards = input.drop(2).windowed(5, 6).map(::Bingo)
    part1(drawnNumbers, bingoBoards)
    part2(drawnNumbers, bingoBoards)
}

fun part2(drawnNumbers: List<Int>, bingoBoards: List<Bingo>) {
    val boards = bingoBoards.toMutableList()
    for (number in drawnNumbers) {
        boards.forEach { board -> board.markNumber(number) }
        val winners = boards.filter { board -> board.checkWin() }
        if (winners.isNotEmpty()) {
            if (boards.size == 1) {
                println(winners[0].sumOfUnmarkedNumbers() * number)
                break
            }
            boards.removeAll(winners)
        }
    }
}

fun part1(drawnNumbers: List<Int>, bingoBoards: List<Bingo>) {
    for (number in drawnNumbers) {
        bingoBoards.forEach { board -> board.markNumber(number) }
        val winner = bingoBoards.firstOrNull { board -> board.checkWin() }
        if (winner != null) {
            println(winner.sumOfUnmarkedNumbers() * number)
            break
        }
    }
}

fun Bingo(rows: List<String>): Bingo {
    val numbers = rows.map { row ->
        row.split(' ')
            .filter { it.isNotBlank() }
            .map { Cell(it.trim().toInt()) }
    }
    return Bingo(numbers)
}

data class Cell(val number: Int, var marked: Boolean = false)

data class Bingo(val rows: List<List<Cell>>) {
    fun markNumber(number: Int) {
        rows.forEach {
            it.forEach { cell ->
                if (cell.number == number) cell.marked = true
            }
        }
    }

    fun checkWin() = checkRows() || checkColumns()

    private fun checkRows() = rows.any { row -> row.all { it.marked } }

    private fun checkColumns(): Boolean {
        rows.forEachIndexed { i, _ ->
            val counter = (0..<5).count { rows[it][i].marked }
            if (counter == 5) return true
        }
        return false
    }

    fun sumOfUnmarkedNumbers() = rows.sumOf {
        it.sumOf { cell ->
            if (!cell.marked) cell.number else 0
        }
    }
}
