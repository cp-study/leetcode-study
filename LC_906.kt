package com


internal class Solution {
    fun superpalindromesInRange(L: String, R: String): Int {
        val palindromes: MutableList<Long> = ArrayList()
        val low = L.toLong()
        val high = R.toLong()
        var res = 0
        for (i in 1..9) {
            palindromes.add(i.toLong())
        }
        for (i in 1..9999) {
            val left = i.toLong().toString()
            val right = StringBuilder(left).reverse().toString()
            palindromes.add((left + right).toLong())
            for (d in 0..9) {
                palindromes.add((left + d + right).toLong())
            }
        }
        for (palindrome in palindromes) {
            val square = palindrome * palindrome
            if (!isPalindrome(square.toString())) {
                continue
            }
            if (square in low..high) {
                res++
            }
        }
        return res
    }

    private fun isPalindrome(str: String): Boolean {
        var i = 0
        var j = str.length - 1
        while (i < j) {
            if (str[i] != str[j]) {
                return false
            }
            i++
            j--
        }
        return true
    }
}
