import kotlin.math.max
import kotlin.math.min

class LC_233 {
    fun countDigitOne(n: Int): Int {
        var tot = 0
        var idx = 1
        while (idx <= n) {
            val div = idx * 10
            tot += (n / div) * idx + min(max(n % div - idx + 1, 0), idx)
            idx *= 10
        }
        return tot
    }
}