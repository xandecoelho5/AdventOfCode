import java.io.File

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/main/resources", "$name.txt").readLines()

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
