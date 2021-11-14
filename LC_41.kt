import kotlin.math.abs

class LC_41 {
    fun firstMissingPositive(nums: IntArray): Int {
        val n = nums.size
        for (i in 0 until n) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1
            }
        }
        for (i in 0 until n) {
            var num = abs(nums[i])
            if(num > n) continue
            num--
            if (nums[num]>0){
                nums[num] = -1 * nums[num]
            }
        }
        for (i in 0 until n) {
            if (nums[i] >= 0) {
                return i + 1
            }
        }
        return n + 1
    }
}