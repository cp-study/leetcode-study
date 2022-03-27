import kotlin.math.abs

class Solution {
    private var min = Int.MAX_VALUE
    private var bucketSize = 0

    fun minimumIncompatibility(nums: IntArray, k: Int): Int {
        val n = nums.size
        bucketSize = n / k
        val sets: MutableList<MutableSet<Int>> = ArrayList()
        for (i in 0 until k) {
            sets.add(HashSet())
        }
        backtrack(nums, 0, sets, 0)
        return if (min == Int.MAX_VALUE) -1 else min
    }

    private fun backtrack(nums: IntArray, idx: Int, sets: List<MutableSet<Int>>, acc: Int) {
        var acc = acc
        if (idx >= nums.size) {
            min = min.coerceAtMost(acc)
            return
        }
        val visited: MutableSet<Set<Int>> = HashSet()
        for (set in sets) {
            if (set.contains(nums[idx]) || set.size == bucketSize || visited.contains(set)) continue
            val impact = computeImpact(set, nums[idx])
            acc += impact
            if (acc < min) {
                set.add(nums[idx])
                backtrack(nums, idx + 1, sets, acc)
                set.remove(nums[idx])
            }
            acc -= impact
            visited.add(set)
        }
    }

    private fun computeImpact(set: Set<Int>, num: Int): Int {
        if (set.isEmpty()) return 0
        if (set.size == 1) return abs(num - set.first())
        val lo = set.min()!!
        val hi = set.max()!!
        if (num < lo) return lo - num
        return if (num > hi) num - hi else 0
    }
}
