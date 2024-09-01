from functools import lru_cache
from typing import List

class Solution:
    def maxEnergyBoost(self, energyDrinkA: List[int], energyDrinkB: List[int]) -> int:
        n = len(energyDrinkA)
        @lru_cache
        def dp(idx: int, bef: int) -> int:
            if idx >= n:
                return 0
            ret = 0
            if bef == 0:
                ret = max(dp(idx+1, 0) + energyDrinkA[idx], dp(idx+1, 1))
            elif bef == 1:
                ret = max(dp(idx + 1, 1) + energyDrinkB[idx], dp(idx + 1, 0))
            elif bef == -1:
                ret = max(dp(idx+1, 0) + energyDrinkA[idx], dp(idx+1, 1) + energyDrinkB[idx])
            return ret

        return dp(0, -1)