package day1

class Part2(private val input: List<String>) {
    private val listOne = mutableListOf<Int>()
    private val listTwo = mutableListOf<Int>()

    fun calculateSimilarityScore(): Int {
        input.forEach { splitStringIntoNumbers(it) }

        var similarityScore = 0
        listOne.forEach { item ->
            val occurrences = listTwo.count { it == item }
            similarityScore += (item * occurrences)
        }
        return similarityScore
    }

    private fun splitStringIntoNumbers(inputLine: String) {
        listOne.add(inputLine.substringBefore(" ").toInt())
        listTwo.add(inputLine.substringAfter(" ").trim().toInt())
    }
}