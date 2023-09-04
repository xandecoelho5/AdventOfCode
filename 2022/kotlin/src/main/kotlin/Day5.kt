import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Stack

fun main() {
    val crateMover9000: CrateMover = crateMoverFromFile()
    for (move in crateMover9000.moves) {
        crateMover9000.doMove9000(move)
    }
    printTopCrates(crateMover9000)

    val crateMover9001: CrateMover = crateMoverFromFile()
    for (move in crateMover9001.moves) {
        crateMover9001.doMove9001(move)
    }
    printTopCrates(crateMover9001)
}

private fun crateMoverFromFile(): CrateMover {
    val crateMover = CrateMover(ArrayList(), ArrayList())
    BufferedReader(InputStreamReader(FileInputStream("src/main/resources/day5.txt"))).use { br ->
        var line: String?
        while (br.readLine().also { line = it } != null) {
            if (crateMover.stacks.isEmpty()) {
                crateMover.initializeStacks(getQuantityOfStacks(line!!))
            }
            if (line!!.contains("[")) {
                crateMover.addToStacks(line!!)
            } else if (line!!.startsWith("move")) {
                crateMover.moves.add(Move.from(line!!))
            }
        }
    }
    return crateMover
}

private fun printTopCrates(crateMover: CrateMover) {
    val sb = StringBuilder()
    for (stack in crateMover.stacks) {
        sb.append(stack.peek().substring(1, 2))
    }
    println(sb)
}

private fun getQuantityOfStacks(line: String): Int {
    var res = 0
    var i = 0
    while (i <= line.length - 3) {
        res++
        i++
        i += 3
    }
    return res
}

private data class Move(val quantity: Int, val from: Int, val to: Int) {
    companion object {
        fun from(line: String): Move {
            val split = line.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            return Move(split[1].toInt(), split[3].toInt() - 1, split[5].toInt() - 1)
        }
    }
}

private class CrateMover(
    val stacks: MutableList<Stack<String>>,
    val moves: MutableList<Move>
) {
    fun initializeStacks(numStacks: Int) {
        for (i in 0..<numStacks) {
            stacks.add(Stack())
        }
    }

    fun addToStacks(line: String) {
        var i = 0
        var j = 0
        while (i <= line.length - STACK_LABEL_LENGTH) {
            val substr = line.substring(i, i + STACK_LABEL_LENGTH)
            if (substr.contains("[")) {
                stacks[(i - j) / STACK_LABEL_LENGTH].add(0, substr)
            }
            i++
            i += STACK_LABEL_LENGTH
            j++
        }
    }

    fun doMove9000(move: Move) {
        for (i in 0..<move.quantity) {
            stacks[move.to].push(stacks[move.from].pop())
        }
    }

    fun doMove9001(move: Move) {
        val auxStack = Stack<String>()
        for (i in 0..<move.quantity) {
            auxStack.push(stacks[move.from].pop())
        }
        for (i in 0..<move.quantity) {
            stacks[move.to].push(auxStack.pop())
        }
    }

    companion object {
        private const val STACK_LABEL_LENGTH = 3
    }
}

