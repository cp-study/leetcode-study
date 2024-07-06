class LC_1881 {
    fun maxValue(n: String, x: Int): String {
        when {
            n[0] == '-' -> {
                for (i in 1..<n.length) {
                    if (n[i] - '0' > x) {
                        return n.substring(0, i) + x + n.substring(i)
                    }
                }
                return n + x
            }
            else -> {
                for (i in n.indices) {
                    if (n[i] - '0' < x) {
                        return n.substring(0, i) + x + n.substring(i)
                    }
                }
                return n + x
            }
        }
    }
}