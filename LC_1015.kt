class Solution {
    fun smallestRepunitDivByK(k: Int): Int {
        if (k%2 == 0 || k%5 == 0) return -1
        else {
            val hit = BooleanArray(k) { false }
            var ans = 0
            var n = 0
            while (true) {
                ans ++
                n = (n * 10 + 1) % k
                if(n == 0) return ans
                if(hit[n]) return -1
                hit[n] = true
            }
        }
    }
}