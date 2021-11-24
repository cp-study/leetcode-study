import java.util.*

class Solution {
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        val endwordIdx = wordList.indexOf(endWord)
        if (endwordIdx==-1) {
            return 0
        }
        var ans :Int = 0
        val queue = LinkedList<Pair<String, Int>>();
        queue.add(Pair(beginWord, 1))
        val visit = BooleanArray(wordList.size)
        while (!queue.isEmpty()) {
            val cur = queue.pop()
            queue.addAll(getNext(cur, visit, wordList))
            if(visit[endwordIdx]) {
                ans = cur.second+1
                break
            }
        }
        return ans
    }

    private fun getNext(cur: Pair<String, Int>, visit: BooleanArray, wordList: List<String>): List<Pair<String, Int>> {
        val res = mutableListOf<Pair<String,Int>>()
        for (i in wordList.indices){
            if(!visit[i] and isOneDiff(cur.first, wordList[i])){
                visit[i] = true
                res.add(Pair(wordList[i], cur.second+1))
            }
        }
        return res
    }

    private fun isOneDiff(str1: String, str2: String): Boolean {
        var cnt: Int = 0
        for (i in str1.indices) {
            if (str1[i] != str2[i]) {
                cnt += 1
            }
            if (cnt > 1) return false
        }
        return true
    }
}

fun main() {
    val arr = arrayOf("hot","dot","dog","lot","log","cog").toList()
    Solution().ladderLength("hit","cog", arr)
}
