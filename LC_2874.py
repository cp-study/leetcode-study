from typing import List


class Solution:
    def maximumTripletValue(self, nums: List[int]) -> int:
        left_max = [0] * len(nums)
        right_max = [0] * len(nums)
        left_max[0] = nums[0]
        right_max[-1] = nums[-1]

        for i, val in enumerate(nums[1:], start=1):
            left_max[i] = max(val, left_max[i-1])

        for i, val in reversed(list(enumerate(nums[:-1]))):
            print(i)
            right_max[i] = max(val, right_max[i+1])


        ans = 0
        for i, val in enumerate(nums[1:-1], start=1):
            ans = max(ans, (left_max[i-1] - val) * right_max[i+1])

        return ans


s = Solution()
s.maximumTripletValue([1,2,3,4,5])