fun main() {
    val lines = readInput("day8").toMutableList()
    println(accumulate(lines))

    var indexToChange = 0
    val setOperations = hashSetOf<Int>()
    for (i in lines.indices) {
        val op = lines[i].split(" ")[0]
        if (op == "acc") continue

        val newLines = lines.toMutableList()

        newLines[i] = if (op == "jmp") lines[i].replace("jmp", "nop")
        else lines[i].replace("nop", "jmp")

        var index = 0
        setOperations.clear()
        while (index < newLines.size) {
            if (!setOperations.add(index)) break

            val (operation, argument) = newLines[index].split(" ")
            if (operation == "jmp") {
                index += argument.toInt()
                continue
            }
            index++
        }
        if (index == newLines.size) {
            indexToChange = i
            break
        }
    }

    val op = lines[indexToChange].split(" ")[0]
    lines[indexToChange] = if (op == "jmp") lines[indexToChange].replace("jmp", "nop")
    else lines[indexToChange].replace("nop", "jmp")

    println(accumulate(lines))
}

private fun accumulate(lines: List<String>): Int {
    var accumulator = 0
    val setOperations = hashSetOf<Int>()

    var index = 0
    while (index < lines.size) {
        if (!setOperations.add(index)) break

        val (operation, argument) = lines[index].split(" ")
        when (operation) {
            "acc" -> accumulator += argument.toInt()
            "jmp" -> {
                index += argument.toInt()
                continue
            }
        }
        index++
    }
    return accumulator
}
