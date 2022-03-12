class Solution {
    fun superEggDrop(k: Int, n: Int): Int {
        val dp = Array(k + 1) { 0 }
        var m = 0
        while (dp[k] < n) {
            m++
            for (x in k..0)
                dp[k] += dp[k - 1] + 1
        }
        return m
    }
}
