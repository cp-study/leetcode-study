class Solution {

    fun calculate(s: String): Int {
        var acc = 0
        var i = 0

        val signStack = mutableListOf<Int>()
        var parenSigns = 1
        var prevSign = 1

        while (i < s.length) {
            val c = s[i]
            when (c) {
                ' ' -> {
                }
                '(' -> {
                    signStack.add(prevSign)
                    if (prevSign < 0) parenSigns *= -1
                    prevSign = 1
                }
                ')' -> if (signStack.removeLast() < 0) parenSigns *= -1
                '+' -> prevSign = 1
                '-' -> prevSign = -1
                else -> {
                    var n = 0
                    while (i < s.length && s[i] in '0'..'9') {
                        n = n * 10 + (s[i] - '0')
                        i++
                    }
                    i--
                    acc += n * parenSigns * prevSign
                }
            }
            i++
        }

        return acc
    }
}

fun main() {
    Solution().calculate("1+3")
}
