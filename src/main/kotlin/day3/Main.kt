package day3

import java.io.File

fun main() {
    val input = File("src/main/kotlin/day3/input.txt").readText()

    val result = Part2(input).sumUncorruptedInstructions()
    println("Result is: $result")
}
