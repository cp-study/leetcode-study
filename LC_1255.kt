class Solution {
   private fun sol(words: Array<String>, letters: IntArray, score: IntArray, idx: Int): Int {
        if (idx == words.size) return 0
        val notInclude = sol(words, letters, score, idx + 1)

        var res = 0
        val word = words[idx]
        var flag = true
        for (ch in word) {
            if (letters[ch - 'a'] == 0) {
                flag = false
            }
            letters[ch - 'a']--
            res += score[ch - 'a']
        }
        var tot = 0
        if (flag) tot = res + sol(words, letters, score, idx + 1)
        for (element in word) {
            letters[element - 'a']++
        }
        return tot.coerceAtLeast(notInclude)
    }

    fun maxScoreWords(words: Array<String>, letters: CharArray, score: IntArray): Int {
        val farr = IntArray(26)
        for (i in letters.indices) {
            val ch = letters[i]
            farr[ch - 'a']++
        }
        return sol(words, farr, score, 0)
    }
}
