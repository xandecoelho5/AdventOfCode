import java.io.File

typealias Color = String
typealias Rule = Set<String>

private const val SHINY_GOLD = "shiny gold"

fun main() {
    val rules1: Map<Color, Rule> = buildBagTree()
    val containers = findContainersDFS(rules1)
    println(containers.size)

    val rules2 = buildBagTree2()
    val result = rules2.getChildrenCountBFS(SHINY_GOLD)
    println(result)
}

val digits = "\\d".toRegex()
private fun Map<Color, Rule>.getChildrenCountBFS(color: Color): Int {
    val children = getOrDefault(color, setOf())
    if(children.isEmpty()) return 0
    return children.sumOf { child ->
        val count = digits.find(child)?.value?.toInt() ?: 0
        count + count * getChildrenCountBFS(child.replace(digits, "").trim())
    }
}

fun findContainersDFS(rules: Map<Color, Rule>): Set<Color> {
    var known = setOf(SHINY_GOLD)
    var next = setOf(SHINY_GOLD) + rules[SHINY_GOLD]!!
    while (true) {
        val toFind = next - known
        if (toFind.isEmpty()) break
        known = known + next
        next = toFind.mapNotNull { rules[it] }.flatten().toSet()
    }
    return known - SHINY_GOLD
}

private fun buildBagTree(): Map<Color, Rule> {
    val rules = mutableMapOf<Color, Rule>()
    File("src/main/resources/day7.txt")
        .forEachLine { line ->
            val (parent, allChildren) = line
                .replace(Regex("\\d+"), "")
                .replace(Regex("bags?\\.?"), "")
                .split("contain")
                .map { it.trim() }
            val childrenColors = allChildren.split(",").map { it.trim() }.toSet()
            for (childColor in childrenColors) {
                rules.compute(childColor) { _, parents ->
                    if (parents == null) setOf(parent) else parents + parent
                }
            }
        }
    return rules
}

private fun buildBagTree2(): Map<Color, Rule> {
    val rules = hashMapOf<Color, Rule>()
    File("src/main/resources/day7.txt")
        .forEachLine { line ->
            val (parent, allChildren) = line
                .replace(Regex("bags?\\.?"), "")
                .split("contain")
                .map { it.trim() }
            val rule = if (allChildren.contains("no other")) emptySet() else
                allChildren.split(",").map { it.trim() }.toSet()
            rules[parent] = rule
        }
    return rules
}
