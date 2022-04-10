from functools import lru_cache
from typing import List


class Solution:
    def canCross(self, stones: List[int]) -> bool:

        @lru_cache(None)
        def jump(idx, k):
            if idx == len(stones) - 1:
                return True

            i = idx + 1
            while i < len(stones) and stones[i] - stones[idx] < k - 1:
                i += 1
            while i < len(stones) and k + 1 >= stones[i] - stones[idx]:
                if jump(i, stones[i] - stones[idx]):
                    return True
                i += 1

            return False

        return jump(0, 0)
