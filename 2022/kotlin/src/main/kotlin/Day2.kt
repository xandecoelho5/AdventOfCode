import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

fun main() {
    val scoreMap = mutableMapOf(
        'A' to mutableMapOf('X' to 1 + 3, 'Y' to 2 + 6, 'Z' to 3),
        'B' to mutableMapOf('X' to 1, 'Y' to 2 + 3, 'Z' to 3 + 6),
        'C' to mutableMapOf('X' to 1 + 6, 'Y' to 2, 'Z' to 3 + 3)
    )

    val firstScore = getScoreAccordingToOptions(scoreMap, "src/main/resources/day2.txt")
    println("Total score of 1st part: $firstScore")
    scoreMap['A']!!.replace('X', 3)
    scoreMap['A']!!.replace('Y', 1 + 3)
    scoreMap['A']!!.replace('Z', 2 + 6)
    scoreMap['B']!!.replace('X', 1)
    scoreMap['B']!!.replace('Y', 2 + 3)
    scoreMap['B']!!.replace('Z', 3 + 6)
    scoreMap['C']!!.replace('X', 2)
    scoreMap['C']!!.replace('Y', 3 + 3)
    scoreMap['C']!!.replace('Z', 1 + 6)
    val secondScore = getScoreAccordingToOptions(scoreMap, "src/main/resources/day2.txt")
    println("Total score of 2nd part: $secondScore")
}

private fun getScoreAccordingToOptions(scoreMap: Map<Char, Map<Char, Int>>, filename: String): Int {
    var scoreSum = 0
    BufferedReader(InputStreamReader(FileInputStream(filename))).use { br ->
        var line: String?
        while (br.readLine().also { line = it } != null) {
            val options =
                line!!.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            val enemyOption = options[0][0]
            val myOption = options[1][0]
            scoreSum += scoreMap[enemyOption]!![myOption]!!
        }
    }
    return scoreSum
}
