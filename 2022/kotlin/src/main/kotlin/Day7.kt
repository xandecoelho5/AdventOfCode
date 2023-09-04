fun main() {
    val lines = readInput("day7")
    solve("part1", lines)
    solve("part2", lines)
}

private fun solve(part: String, commands: List<String>) {
    val rootNode = Node()
    rootNode.name = "/"
    rootNode.isDirectory = true
    var currentPosition: Node = rootNode
    for (command in commands) {
        if (command[0] == '$') {
            currentPosition = processCommand(command, rootNode, currentPosition)
        } else {
            processFile(command, currentPosition)
        }
    }
    val totalSize = updateDirectorySize(rootNode)
    if ("part1" == part) {
        println(sumOfDirectorySizeLessThanThreshold(rootNode, 100000, 0))
    } else {
        val totalSpace = 70000000
        val freeSpaceNeeded = 30000000
        val currentlyFreeSpace = totalSpace - totalSize
        val moreSpaceNeeded = freeSpaceNeeded - currentlyFreeSpace
        println(findSmallestDirectoryGreaterThanSize(rootNode, null, moreSpaceNeeded)?.size)
    }
}

private fun processCommand(
    command: String,
    root: Node,
    currentPosition: Node
): Node {
    var newCurrentPosition: Node = currentPosition
    val splitCommand = command.split(" ")
    if ("cd" == splitCommand[1]) {
        val param = splitCommand[2]
        if (".." == param) {
            newCurrentPosition = currentPosition.parent!!
        } else if ("/" == param) {
            newCurrentPosition = root
        } else {
            if (currentPosition.containsChild(param)) {
                newCurrentPosition = currentPosition.getChild(param)!!
            }
        }
    }
    return newCurrentPosition
}

private fun processFile(command: String, currentPosition: Node) {
    val splitCommand = command.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
        .toTypedArray()
    if (!currentPosition.containsChild(splitCommand[1])) {
        val newNode = Node()
        newNode.name = splitCommand[1]
        newNode.parent = currentPosition
        currentPosition.addChild(newNode)
        if ("dir" == splitCommand[0]) {
            newNode.isDirectory = true
        } else {
            newNode.isDirectory = false
            newNode.size = splitCommand[0].toInt()
        }
    }
}

private fun updateDirectorySize(node: Node): Int {
    if (!node.isDirectory) {
        return node.size
    }
    var subSize = 0
    for (child in node.children) {
        subSize += updateDirectorySize(child)
    }
    node.size = subSize
    return subSize
}

private fun sumOfDirectorySizeLessThanThreshold(
    node: Node,
    threshold: Int,
    totalSize: Int
): Int {
    var size = totalSize
    if (!node.isDirectory) {
        return size
    }
    for (child in node.children) {
        size = sumOfDirectorySizeLessThanThreshold(child, threshold, size)
    }
    return if (node.size <= threshold) {
        node.size + size
    } else size
}

private fun findSmallestDirectoryGreaterThanSize(
    node: Node,
    candidateNode: Node?,
    targetSize: Int
): Node? {
    var candidate: Node? = candidateNode
    if (!node.isDirectory) {
        return candidate
    }
    for (child in node.children) {
        candidate = findSmallestDirectoryGreaterThanSize(child, candidate, targetSize)
    }
    if (node.size < targetSize) {
        return candidate
    }
    return if (candidate == null || node.size <= candidate.size) {
        node
    } else candidate
}

internal class Node {
    var name = ""
    var parent: Node? = null
    var isDirectory = true
    var size = 0
    var children: MutableSet<Node> = HashSet()
    fun containsChild(name: String): Boolean {
        for (child in children) {
            if (child.name == name) {
                return true
            }
        }
        return false
    }

    fun getChild(name: String): Node? {
        for (child in children) {
            if (child.name == name) {
                return child
            }
        }
        return null
    }

    fun addChild(child: Node) {
        children.add(child)
    }
}

