from typing import List


class Solution:
    def minimumMountainRemovals(self, nums: List[int]) -> int:

        lis_dp = [1] * len(nums)
        lds_dp = [1] * len(nums)

        for i in range(1, len(nums)):
            for j in range(i):
                if nums[i] > nums[j]:
                    lis_dp[i] = max(lis_dp[i], lis_dp[j] + 1)

        for i in range(len(nums) - 2, -1, -1):
            for j in range(i + 1, len(nums)):
                if nums[i] > nums[j]:
                    lds_dp[i] = max(lds_dp[i], lds_dp[j] + 1)

        ans = 1987654321
        for i in range(1, len(nums) - 1):
            ans = min(ans, len(nums) - (lis_dp[i] + lds_dp[i] - 1))

        return ans
