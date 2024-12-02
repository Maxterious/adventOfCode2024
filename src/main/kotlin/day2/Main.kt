package day2

import java.io.File

fun main() {
    val input = File("src/main/kotlin/day2/input.txt").readLines()

    val result = Part1(input).calculateSafeReportAmount()
    println("Safe report count is: $result")
}