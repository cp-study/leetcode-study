class Solution {
    fun distinctNames(ideas: Array<String>): Long {
        val count: Array<HashSet<Int>?> = arrayOfNulls(26)
        for (i in 0..25) count[i] = HashSet()
        for (s in ideas) count[s[0] - 'a']!!.add(s.substring(1).hashCode())
        var res: Long = 0
        for (i in 0..25)
            for (j in i + 1..25) {
            var c1: Long = 0
            var c2: Long = 0
            for (c in count[i]!!) 
                if (!count[j]!!.contains(c)) c1++
            for (c in count[j]!!) 
                if (!count[i]!!.contains(c)) c2++
            res += c1 * c2
        }
        return res * 2
    }
}
