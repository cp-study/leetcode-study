from functools import lru_cache
from typing import List


class Solution:
    def maximumsSplicedArray(self, nums1: List[int], nums2: List[int]) -> int:
        def solve(list1, list2):
            dp = [0 for _ in range(3)]
            dp[0] = list1[0]
            dp[1] = list2[0]

            for i in range(1, len(list1)):
                dp[2] = max(dp[1] + list1[i], dp[2] + list1[i])
                dp[1] = max(dp[0] + list2[i], dp[1] + list2[i])
                dp[0] = dp[0] + list1[i]

            return max(dp)

        return max(solve(nums1, nums2), solve(nums2, nums1))
