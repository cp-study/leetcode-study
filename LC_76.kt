class LC_76 {
    fun minWindow(s: String, t: String): String {
        val windowMap = mapOf<Char,Boolean>()
        val lenS = s.length
        val lenT = t.length

        val freqs = IntArray(128){ 0 }
        for(ch in t){
            ++freqs[ch.toInt()]
        }

        var shortest = ""
        var count = lenT
        var lo = 0
        var hi = 0

        while(hi < lenS){
            if(freqs[s[hi].code]-- > 0) --count

            while(count == 0){
                if(shortest.isEmpty() || hi - lo + 1 < shortest.length){
                    shortest = s.substring(lo, hi + 1)
                }

                if(++freqs[s[lo].code] > 0) ++count
                ++lo
            }

            ++hi
        }
        return shortest
    }
}