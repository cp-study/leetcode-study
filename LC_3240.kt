
import jdk.internal.org.jline.utils.DiffHelper.diff
import kotlin.math.min

class LC_3240 {
    fun minFlips(grid: Array<IntArray>): Int {
        val row = grid.size
        val col = grid[0].size

        var totalOnes = 0
        var flips = 0
        var diff = 0

        for (i in 0..<row / 2) {
            for (j in 0..<col / 2) {
                val onesCount =
                    grid[i][j] + grid[i][col - 1 - j] + grid[row - 1 - i][j] + grid[row - 1 - i][col - 1 - j]
                flips += min(onesCount, (4 - onesCount))
            }
        }

        if (col % 2 > 0) {
            for (i in 0..<row / 2) {
                if (grid[i][col / 2] != grid[row - 1 - i][col / 2]) {
                    diff++
                }
                totalOnes += grid[i][col / 2] + grid[row - 1 - i][col / 2]
            }
        }

        if (row % 2 > 0) {
            for (j in 0..<col / 2) {
                if (grid[row / 2][j] != grid[row / 2][col - 1 - j]) {
                    diff++
                }
                totalOnes += grid[row / 2][j] + grid[row / 2][col - 1 - j]
            }
        }

        if (col % 2 > 0 && row % 2 > 0) {
            flips += grid[row / 2][col / 2]
        }

        if (diff == 0 && totalOnes % 4 > 0) {
            flips += 2
        }

        return (flips + diff)
    }
}