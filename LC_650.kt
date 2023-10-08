package com

class Solution {
    fun minSteps(n: Int): Int {
        var tmp = n
        var res = 0
        for (i in 2..tmp) {
            while (tmp % i == 0) {
                res += i
                tmp /= i
            }
        }
        return res;
    }
}

