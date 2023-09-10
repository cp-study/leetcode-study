package com

class Solution : SolBase() {
    fun rand10(): Int {
        var tmp = 40
        while(tmp >= 40) {
            tmp = (rand7() -1) * 7 + (rand7() - 1)
        }
        return tmp % 10 + 1
    }
}
