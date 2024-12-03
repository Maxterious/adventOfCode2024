package day2

class Part2(private val reports: List<String>) {
    private var safeReportCount = 0

    fun calculateSafeReportAmount(): Int {
        reports.forEach { report ->
            val reportLevels = report.split(" ")
                .map { it.trim().toInt() }
            if (handleReportChecks(reportLevels)) safeReportCount++
        }
        return safeReportCount
    }

    private fun handleReportChecks(report: List<Int>): Boolean {
        val isLevelIncreasing = isLevelIncreasing(report)

        if (isSafeReport(report, isLevelIncreasing)) return true
        else {
            val unsafeLevelIndex = getFirstUnsafeLevel(report, isLevelIncreasing)
            return removeOneBadLevel(report, isLevelIncreasing, unsafeLevelIndex)
        }
    }

    private fun isLevelIncreasing(report: List<Int>): Boolean {
        return isMostlyIncreasing(report)
    }

    private fun isMostlyIncreasing(list: List<Int>): Boolean {
        var exceptions = 0
        list.zipWithNext().forEach { (a, b) ->
            if (a >= b) exceptions++
            if (exceptions > 1) return false
        }
        return true
    }

    private fun isSafeReport(report: List<Int>, isLevelIncreasing: Boolean): Boolean {
        var isReportSafe = true

        loop@ for (i in 0 until report.size - 1) {
            if (!isReportSafe) break@loop

            isReportSafe = if (isLevelIncreasing) {
                isSafeDistance(report[i + 1] - report[i])
            } else {
                isSafeDistance(report[i] - report[i + 1])
            }
        }
        return isReportSafe
    }

    private fun getFirstUnsafeLevel(report: List<Int>, isLevelIncreasing: Boolean): Int {
        for (i in 0 until report.size - 1) {
            if (isLevelIncreasing) {
                if (!isSafeDistance(report[i + 1] - report[i])) return i
            } else {
                if (!isSafeDistance(report[i] - report[i + 1])) return i
            }
        }
        return -1
    }

    private fun removeOneBadLevel(report: List<Int>, isLevelIncreasing: Boolean, badLevelIndex: Int): Boolean {
        val reportWithoutFirstUnsafeLevel = report.toMutableList()
        val reportWithoutSecondUnsafeLevel = report.toMutableList()

        if (isLevelIncreasing) {
            reportWithoutFirstUnsafeLevel.removeAt(badLevelIndex)
            reportWithoutSecondUnsafeLevel.removeAt(badLevelIndex + 1)
        } else {
            reportWithoutFirstUnsafeLevel.removeAt(badLevelIndex)
            reportWithoutSecondUnsafeLevel.removeAt(badLevelIndex + 1)
        }
        return isSafeReport(reportWithoutFirstUnsafeLevel, isLevelIncreasing) ||
                isSafeReport(reportWithoutSecondUnsafeLevel, isLevelIncreasing)
    }

    private fun isSafeDistance(levelDifference: Int): Boolean {
        return levelDifference in 1..3
    }

}