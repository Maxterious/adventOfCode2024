package day2

import java.io.File

fun main() {
    val input = File("src/main/kotlin/day2/input.txt").readLines()

//    val result = Part1(input).calculateSafeReportAmount()
    val result = Part2(input).calculateSafeReportAmount()
    println("Safe report count is: $result")
}