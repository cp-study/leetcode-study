class LC1388 {
    fun maxSizeSlices(slices: IntArray): Int {
        val m = slices.size
        val n = m / 3
        val slices1 = slices.copyOfRange(0, m - 1)
        val slices2 = slices.copyOfRange(1, m)
        return Math.max(maxSum(slices1, n), maxSum(slices2, n))
    }

    private fun maxSum(arr: IntArray, n: Int): Int { // max sum when pick `n` non-adjacent elements from `arr`
        val m = arr.size
        val dp =
            Array(m + 1) { IntArray(n + 1) } // dp[i][j] is maximum sum which we pick `j` elements from linear array `i` elements
        // Case j = 0 (pick 0 elements): dp[i][0] = 0
        // Case i = 0 (array is empty): dp[0][j] = 0
        for (i in 1..m) {
            for (j in 1..n) {
                if (i == 1) { // array has only 1 element
                    dp[i][j] = arr[0] // pick that element
                } else {
                    dp[i][j] = Math.max(
                        dp[i - 1][j],  // don't pick element `ith`
                        dp[i - 2][j - 1] + arr[i - 1] // pick element `ith` -> dp[i-2][j-1] means choose `j-1` elements from array `i-2` elements
                        //   because we exclude adjacent element `(i-1)th`
                    )
                }
            }
        }
        return dp[m][n]
    }
}
