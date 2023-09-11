import java.io.File

fun main() {
    val answers = File("src/main/resources/day6.txt").readText().trim()
        .split("\n\n", "\r\n\r\n")

    val sumOfCounts = answers.sumOf { a -> a.filter { it.isLetter() }.toSet().size }
    println(sumOfCounts)

    val countGroupAnswers = answers.sumOf { groupAnswers ->
        groupAnswers.split("\n", "\r\n")
            .map { it.toSet() }
            .reduce { acc, answerSet -> acc.intersect(answerSet) }.size
    }
    println(countGroupAnswers)
}

fun countGroupAnswer(answers: List<String>): Int {
    val letterMap: MutableMap<Char, Int> = hashMapOf()
    var count = 0
    for (answer in answers) {
        val splitAnswers = answer.split("\n", "\r\n")
        for (letter in splitAnswers.joinToString(" ")) {
            if (letter.isLetter()) letterMap[letter] = letterMap.getOrPut(letter) { 0 } + 1
        }
        for ((_, value) in letterMap) {
            if (value == splitAnswers.size) count++
        }
        letterMap.clear()
    }
    return count
}
