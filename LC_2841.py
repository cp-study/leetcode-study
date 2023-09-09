from collections import defaultdict
from typing import List


class Solution:
    def maxSum(self, nums: List[int], m: int, k: int) -> int:
        maxSum, sum = 0, 0
        cnt = defaultdict(int)
        uniqueCount = 0
        left, right = 0, 0

        while right < len(nums):
            if right - left == k:
                cnt[nums[left]] -= 1
                if cnt[nums[left]] == 0:
                    uniqueCount -= 1
                sum -= nums[left]
                left += 1

            if cnt[nums[right]] == 0:
                uniqueCount += 1
            cnt[nums[right]] += 1
            sum += nums[right]

            right += 1

            if right - left == k and uniqueCount >= m:
                maxSum = max(maxSum, sum)

        return maxSum
