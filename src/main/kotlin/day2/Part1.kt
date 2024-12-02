package day2

class Part1(private val reports: List<String>) {
    private var safeReportCount = 0

    fun calculateSafeReportAmount(): Int {
        reports.forEach { report ->
            val reportLevels = report.split(" ")
                .map { it.trim().toInt() }
            isValidReport(reportLevels)
        }
        return safeReportCount
    }

    private fun isValidReport(report: List<Int>) {
        var isReportSafe = true
        val isLevelIncreasing = report[0] < report[1]


        loop@ for (i in 0 until report.size - 1) {
                if (!isReportSafe) break@loop
                isReportSafe = if (isLevelIncreasing) {
                    isSafeDistance(report[i + 1] - report[i])
                } else {
                    isSafeDistance(report[i] - report[i + 1])
                }
        }
        if (isReportSafe) safeReportCount++
    }

    private fun isSafeDistance(levelDifference: Int): Boolean {
        return levelDifference in 1..3
    }
}