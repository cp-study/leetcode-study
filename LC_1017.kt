package com

import kotlin.math.abs
import kotlin.math.pow

class Solution {
    fun baseNeg2(n: Int): String {
        var num = 0
        if (n == 0) return "0"
        if (n == 1) return "1"
        val base = 2.0
        var range = 3
        var least = 1
        var most = 1
        while (true) {
            least = most + 1
            most = least + (base.pow(range - 1) - 1).toInt()
            num = n - least + 1
            if (n in least..most) break
            range += 2
        }
        val arr = IntArray(range) { 0 }
        arr[range-1] = 1
        for (i in 1 until range) {
            val rep = 2.0.pow(i).toInt()
            val remain = num % rep
            val cur = if (i%2 == 0) 1 else 0
            arr[i-1] = when(remain) {
                in 1..rep/2 -> cur
                else -> abs(cur-1)
            }
        }
        return arr.reversed().joinToString("")
    }
}

fun main() {
    val sol = Solution()
    print(sol.baseNeg2(4))
}