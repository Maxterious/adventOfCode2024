package day1

class Part1(private val input: List<String>) {
    private val listOne = mutableListOf<Int>()
    private val listTwo = mutableListOf<Int>()

    fun calculateDistanceSum(): Int {
        input.forEach { splitStringIntoNumbers(it) }

        listOne.sort()
        listTwo.sort()
        var totalDistance = 0
        listOne.forEachIndexed { index, _ ->
            totalDistance += calculateNumberDistance(listOne[index], listTwo[index])
        }
        return totalDistance
    }

    private fun splitStringIntoNumbers(inputLine: String) {
        listOne.add(inputLine.substringBefore(" ").toInt())
        listTwo.add(inputLine.substringAfter(" ").trim().toInt())
    }

    private fun calculateNumberDistance(numberOne: Int, numberTwo: Int): Int {
        //bigger number minus smaller number
        return if (numberOne > numberTwo) {
            numberOne - numberTwo
        } else {
            numberTwo - numberOne
        }
    }
}