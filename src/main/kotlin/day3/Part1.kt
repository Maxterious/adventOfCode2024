package day3

class Part1(private val instructions: List<String>) {
    private var sum = 0

    fun sumUncorruptedInstructions(): Int {
        instructions.forEach {
            val fixedMemory = findNonCorruptedMemory(it)
            val extractedNumbers = retrieveNumbersFromMemoryBlocks(fixedMemory)
            sum += multiplyInstructions(extractedNumbers)
        }
        return sum
    }

    private fun findNonCorruptedMemory(badMemory: String): List<String> {
        val regex = Regex("mul\\([0-9]{1,3},[0-9]{1,3}\\)")
        return regex.findAll(badMemory).map { it.value }.toList()
    }

    private fun retrieveNumbersFromMemoryBlocks(validMemoryBlocks: List<String>): List<List<Int>> {
        val regex = Regex("[0-9]{1,3}")
        var validMemoryNumbers: List<List<Int>> = mutableListOf()

        validMemoryBlocks.forEach { validMemBlock ->
            val memoryBlock: List<Int> = regex.findAll(validMemBlock).map { it.value.toInt() }.toList()
            validMemoryNumbers = validMemoryNumbers.plus(listOf(memoryBlock))
        }
        return validMemoryNumbers
    }

    private fun multiplyInstructions(validInstructions: List<List<Int>>): Int {
        var sum = 0
        validInstructions.forEach {
            sum += it[0] * it[1]
        }
        return sum
    }
}