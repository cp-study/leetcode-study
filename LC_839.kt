package main

class LC839 {
    fun numSimilarGroups(strs: Array<String>): Int {
        // 배열은 300개까지 문자열의 길이도 300까지
        val ans = mutableSetOf<MutableSet<String>>()

        strs.forEach {
            if (ans.isEmpty()) {
                val newSet = mutableSetOf(it)
                ans.add(newSet)
            } else {
                var tmpSetOfSet = mutableSetOf<MutableSet<String>>()
                var flag = false
                for (set in ans) {
                    if (isContainSimilar(it, set)) {
                        tmpSetOfSet.add(set)
                        flag = true
                    }
                }
                if (flag) {
                    val slot: MutableSet<String> = mutableSetOf(it)
                    tmpSetOfSet.forEach { set ->
                        ans.remove(set)
                        slot.addAll(set)
                    }
                    ans.add(slot)
                } else {
                    val newSet = mutableSetOf(it)
                    ans.add(newSet)
                }
            }
        }

        return ans.size
    }

    private fun isContainSimilar(str: String, set: MutableSet<String>): Boolean {
        return set.any { isSimilar(str, it) }
    }

    private fun isSimilar(str1: String, str2: String): Boolean {
        var cnt = 0
        for (i in str1.indices) {
            if (str1[i] != str2[i])
                cnt += 1
            if (cnt > 2)
                return false
        }
        return true
    }
}

fun main() {
    val sol = LC839()
    val input = arrayOf(
        "kccomwcgcs",
        "socgcmcwkc",
        "sgckwcmcoc",
        "coswcmcgkc",
        "cowkccmsgc",
        "cosgmccwkc",
        "sgmkwcccoc",
        "coswmccgkc",
        "kowcccmsgc",
        "kgcomwcccs"
    )
    sol.numSimilarGroups(input)
}
