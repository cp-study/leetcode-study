class LC_31 {
    fun nextPermutation(nums: IntArray): Unit {
        val len = nums.size
        var pivot = -1

        for (i in len - 1 downTo 1) {
            if (nums[i - 1] < nums[i]) {  // 증가하는 부분 찾기
                pivot = i - 1
                break
            }
        }

        if (pivot == -1) {
            nums.reverse()
            return
        }

        for (i in len - 1 downTo 0) {
            if (nums[pivot] < nums[i]) {
                // Swap pivot과 해당 값
                nums[pivot] = nums[i].also { nums[i] = nums[pivot] }
                break
            }
        }

        nums.reverse(pivot + 1, len)
    }
}