import bisect
from typing import List


class Solution:
    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        nums = sorted(set(nums))

        ans = 987654321

        for i in range(len(nums)):
            start = nums[i]
            end = start + n - 1

            idx = bisect.bisect_right(nums, end)
            ans = min(ans, n - idx + i)

        return ans
