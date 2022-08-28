package com

import java.util.*


internal class Solution {
    fun canSeePersonsCount(A: IntArray): IntArray {
        val n = A.size
        val res = IntArray(n)
        val stack: Stack<Int> = Stack<Int>()
        for (i in 0 until n) {
            while (!stack.isEmpty() && A[stack.peek()!!] <= A[i]) res[stack.pop()!!]++
            if (!stack.isEmpty()) res[stack.peek()!!]++
            stack.add(i)
        }
        return res
    }
}
