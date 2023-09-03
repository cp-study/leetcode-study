package com

class Solution {
    fun singleNumber(nums: IntArray): Int {
        var ans = 0
        for (i in 0 until 32) {
            var count = 0
            nums.forEach {
                if(isNumSet(it, i)) count+=1
            }
            if(count % 3 != 0) ans += 1 shl i
        }
        return ans
    }

    private fun isNumSet(num: Int, count: Int): Boolean {
        return num and (1 shl count) != 0
    }
}

fun main() {
    val sol = Solution()
    print(sol.singleNumber(intArrayOf(2,2,3,2)))
}