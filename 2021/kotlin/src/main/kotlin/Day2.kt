fun main() {
    val operations = readInput("day2").map { Operation(it) }
    val positionPart1 = part1(operations)
    println(positionPart1.horizontal * positionPart1.depth)
    val positionPart2 = part2(operations)
    println(positionPart2.horizontal * positionPart2.depth)
}

fun part1(operations: List<Operation>): Position {
    val position = Position(0, 0, 0)
    operations.forEach {
        when (it.operation) {
            "forward" -> position.horizontal += it.value
            "down" -> position.depth += it.value
            "up" -> position.depth -= it.value
        }
    }
    return position
}

fun part2(operations: List<Operation>): Position {
    val position = Position(0, 0, 0)
    operations.forEach {
        when (it.operation) {
            "forward" -> {
                position.horizontal += it.value
                position.depth += position.aim * it.value
            }

            "down" -> position.aim += it.value
            "up" -> position.aim -= it.value
        }
    }
    return position
}

data class Operation(val operation: String, val value: Int)

fun Operation(line: String): Operation {
    val (op, value) = line.split(" ")
    return Operation(op, value.toInt())
}

data class Position(var horizontal: Int, var depth: Int, var aim: Int)
