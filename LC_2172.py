from functools import lru_cache
from typing import List


class Solution:
    def maximumANDSum(self, nums: List[int], numSlots: int) -> int:
        @lru_cache(None)
        def solve(idx, slot1, slot2):
            if idx == len(nums):
                return 0

            ret = 0
            for slot in range(numSlots):
                if slot1 & (1 << slot) == 0:
                    ret = max(ret, solve(idx + 1, slot1 | (1 << slot), slot2) + (nums[idx] & (slot + 1)))
                elif slot2 & (1 << slot) == 0:
                    ret = max(ret, solve(idx + 1, slot1, slot2 | (1 << slot)) + (nums[idx] & (slot + 1)))

            return ret

        return solve(0, 0, 0)
