package main

import java.util.*


class LC2097 {
    var adj: MutableMap<Int, MutableList<Int>?> = HashMap()
    var ans: MutableList<List<Int>> = ArrayList()

    fun validArrangement(pairs: Array<IntArray>): Array<IntArray> {
        val indegree: MutableMap<Int, Int> = HashMap()
        for (pair in pairs) {
            adj.putIfAbsent(pair[0], ArrayList())
            adj[pair[0]]!!.add(pair[1])
            indegree[pair[0]] = indegree.getOrDefault(pair[0], 0) + 1
            indegree[pair[1]] = indegree.getOrDefault(pair[1], 0) - 1
        }
        var start = -1
        for (key in indegree.keys) {
            if (indegree[key]!! > 0) {
                start = key
            }
        }
        if (start == -1) {
            start = pairs[0][0]
        }
        dfs(start)
        val result = Array(pairs.size) { IntArray(pairs[0].size) }
        for (i in ans.indices) {
            result[i][0] = ans[ans.size - i - 1][0]
            result[i][1] = ans[ans.size - i - 1][1]
        }
        return result
    }

    private fun dfs(node: Int) {
        while (adj[node] != null && adj[node]!!.size > 0) {
            val nodes: List<Int>? = adj[node]
            val next = nodes!![nodes.size - 1]
            adj[node]!!.removeAt(nodes.size - 1)
            dfs(next)
            ans.add(listOf(node, next))
        }
    }
}

fun main() {
    val sol = LC2097()

    val pairs = arrayOf(intArrayOf(5,1), intArrayOf(4,5), intArrayOf(9,4), intArrayOf(11,9))
    sol.validArrangement(pairs)
}
