class Solution {
    fun dieSimulator(n: Int, rollMax: IntArray): Int {
        val mod = Math.pow(10.0, 9.0).toLong() + 7
        val dp = Array(n) { LongArray(7) }
        for (i in 0..5) {
            dp[0][i] = 1
        }
        dp[0][6] = 6
        for (i in 1 until n) {
            var sum: Long = 0
            for (j in 0..5) {
                dp[i][j] = dp[i - 1][6]
                // 아직 고려할 필요가 없으면 그대로 가져감
                if (i - rollMax[j] < 0) {
                    sum = (sum + dp[i][j]) % mod
                } else {
                    val tmp = i - rollMax[j] - 1
                    //
                    if (tmp >= 0)
                        dp[i][j] = (dp[i][j] - (dp[tmp][6] - dp[tmp][j])) % mod + mod
                    else
                        dp[i][j] = (dp[i][j] - 1) % mod
                    sum = (sum + dp[i][j]) % mod
                }
            }
            dp[i][6] = sum
        }
        return dp[n - 1][6].toInt()
    }
}

fun main() {
    val sol = Solution()
    val n = 3;
    val rollMax = intArrayOf(1, 1, 1, 2, 2, 3)
    val res = sol.dieSimulator(n, rollMax)
    assert(res == 181)
}
