package day3

import java.io.File

fun main() {
    val input = File("src/main/kotlin/day3/input.txt").readLines()

    val result = Part1(input).sumUncorruptedInstructions()
    println("Result is: $result")
}