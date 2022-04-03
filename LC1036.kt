class Solution {
    private val MAX_VISIT = 20000

    fun isEscapePossible(blocked: Array<IntArray>, source: IntArray, target: IntArray): Boolean {
        val blockedSet: MutableSet<String> = HashSet()
        for (point in blocked) {
            blockedSet.add(getKey(point))
        }
        return canVisit(blockedSet, source, getKey(target)) && canVisit(blockedSet, target, getKey(source))
    }

    private fun getKey(point: IntArray): String {
        return point[0].toString() + "," + point[1]
    }

    private fun canVisit(blocked: Set<String>, source: IntArray, targetKey: String): Boolean {
        val visited: MutableSet<String> = HashSet()
        dfs(blocked, source, targetKey, visited)
        return visited.size >= MAX_VISIT || visited.contains(targetKey)
    }

    private fun dfs(blocked: Set<String>, curr: IntArray, targetKey: String, visited: MutableSet<String>) {
        val (i,j) = curr
        if (i < 0 || j < 0 || i >= 1e6 || j >= 1e6) {
            return
        }
        val key = getKey(curr)
        if (blocked.contains(key)) {
            return
        }
        if (visited.size >= MAX_VISIT || visited.contains(targetKey)) {
            return
        }
        if (visited.contains(key)) {
            return
        }
        visited.add(key)
        for (next in arrayOf(intArrayOf(i - 1, j), intArrayOf(i + 1, j), intArrayOf(i, j - 1), intArrayOf(i, j + 1))) {
            dfs(blocked, next, targetKey, visited)
        }
    }
}
