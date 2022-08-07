from functools import lru_cache
from typing import List


class Solution:
    def countRoutes(self, locations: List[int], start: int, finish: int, fuel: int) -> int:

        @lru_cache(None)
        def dfs(current, remains):
            if remains < 0:
                return 0
            ret = 0
            if current == finish:
                ret += 1

            for i in range(len(locations)):
                if i != current:
                    ret += dfs(i, remains - abs(locations[current] - locations[i]))
            return ret % 1000000007

        return dfs(start, fuel)
