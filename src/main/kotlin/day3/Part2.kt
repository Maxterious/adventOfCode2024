package day3

class Part2(private val input: String) {
    fun sumUncorruptedInstructions(): Int {
            val instructionSensitiveMemory = removeDontRange(input)
            val fixedMemory = findNonCorruptedMemory(instructionSensitiveMemory)
            val extractedNumbers = retrieveNumbersFromMemoryBlocks(fixedMemory)
            return multiplyInstructions(extractedNumbers)
    }

    private fun removeDontRange(rawMemoryLine: String): String {
        var fixedMemory = rawMemoryLine
        var dontIndex: Int

        do {
            dontIndex = fixedMemory.indexOf("don't()")
            if (dontIndex != -1) {
                var nextDoIndex = fixedMemory.indexOf("do()", dontIndex)
                if (nextDoIndex == -1) nextDoIndex =
                    fixedMemory.length else nextDoIndex += "do()".length // Remove to the end of the string
                fixedMemory = fixedMemory.removeRange(dontIndex, nextDoIndex)
            }
        } while (dontIndex != -1) // Repeat as long as "don't()" is found
        return fixedMemory
    }

    private fun findNonCorruptedMemory(badMemory: String): List<String> {
        val regex = Regex("mul\\([0-9]{1,3},[0-9]{1,3}\\)")
        return regex.findAll(badMemory).map { it.value }.toList()
    }

    private fun retrieveNumbersFromMemoryBlocks(validMemoryBlocks: List<String>): List<List<Int>> {
        val regex = Regex("[0-9]{1,3}")
        val validMemoryNumbers = mutableListOf<List<Int>>()

        validMemoryBlocks.forEach { validMemBlock ->
            val memoryBlock: List<Int> = regex.findAll(validMemBlock).map { it.value.toInt() }.toList()
            validMemoryNumbers.add(memoryBlock)
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