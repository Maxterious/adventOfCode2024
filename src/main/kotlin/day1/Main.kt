package day1

import java.io.File

fun main() {
    val input = File("src/main/kotlin/day1/input.txt").readLines()
    val result = Part1(input).calculateDistanceSum()
    println("The total distance is: $result")
}