package com

class Solution {
    fun areSentencesSimilar(sentence1: String, sentence2: String): Boolean {
        val tmp1 = ArrayDeque(sentence1.split(" "))
        val tmp2 = ArrayDeque(sentence2.split(" "))

        val (l, s) = when {
            tmp1.size > tmp2.size -> Pair(tmp1, tmp2);
            tmp1.size < tmp2.size -> Pair(tmp2, tmp1);
            else -> return sentence1 == sentence2
        }

        val ll = l.subList(0, s.size)
        val lr = l.subList(l.size - s.size, l.size)

        if (ll == s || lr == s) return true

        var lFlag = false
        var rFlag = false

        while (l.isNotEmpty() && s.isNotEmpty()) {
            if (l.first() == s.first()) {
                lFlag = true
                l.removeFirst()
                s.removeFirst()
            } else if (l.last() == s.last()) {
                rFlag = true
                l.removeLast()
                s.removeLast()
            } else break
        }

        return lFlag && rFlag && s.isEmpty()
    }
}

fun main() {
    val sol = Solution()
    val s1 = "Eating right now"
    val s2 = "Eating"
    sol.areSentencesSimilar(sentence1 = s1, sentence2 = s2)
}
