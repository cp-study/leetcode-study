import java.math.BigInteger

class LC_1985 {
    fun kthLargestNumber(nums: Array<String>, k: Int): String {
        val comparator = compareBy<String> { it.length }.thenBy { it }
        nums.sortWith(comparator)
        return nums[nums.size-k]
    }

}

fun main() {
    LC_1985().kthLargestNumber(arrayOf("3","6","7","10"), 4)

}