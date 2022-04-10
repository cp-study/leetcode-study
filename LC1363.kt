class LC1363 {
    fun largestMultipleOfThree(digits: IntArray): String? {
        Arrays.sort(digits)
        val remain1Indices: MutableList<Int> = ArrayList(2) 
        val remain2Indices: MutableList<Int> = ArrayList(2)
        for (i in digits.indices) {
            if (digits[i] % 3 == 1 && remain1Indices.size < 2) remain1Indices.add(i)
            else if (digits[i] % 3 == 2 && remain2Indices.size < 2) remain2Indices.add(i)
        }
        val remainSum = Arrays.stream(digits).sum() % 3
        if (remainSum == 1) {
            return if (remain1Indices.size >= 1) getResult(digits, remain1Indices[0], -1)
            else getResult(digits, remain2Indices[0], remain2Indices[1])
        } else if (remainSum == 2) { 
            return if (remain2Indices.size >= 1) getResult(digits, remain2Indices[0], -1)
            else getResult(digits, remain1Indices[0], remain1Indices[1])
        }
        return getResult(digits, -1, -1)
    }

    private fun getResult(digits: IntArray, ban1: Int, ban2: Int): String {
        val sb = StringBuilder()
        for (i in digits.indices.reversed()) {
            if (i == ban1 || i == ban2) continue
            sb.append(digits[i])
        }
        return if (sb.isNotEmpty() && sb[0] == '0') "0" else sb.toString()
    }
}
