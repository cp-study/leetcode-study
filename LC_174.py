from functools import lru_cache
from typing import List


class Solution:
    def calculateMinimumHP(self, dungeon: List[List[int]]) -> int:
        m, n = len(dungeon), len(dungeon[0])

        @lru_cache(None)
        def get_min_hp(x, y):
            if x == m - 1 and y == n - 1:
                return max(1, -dungeon[x][y] + 1)

            if x == m - 1:
                return max(1, -dungeon[x][y] + get_min_hp(x, y + 1))

            if y == n - 1:
                return max(1, -dungeon[x][y] + get_min_hp(x + 1, y))

            return min(max(1, -dungeon[x][y] + get_min_hp(x + 1, y)), max(1, -dungeon[x][y] + get_min_hp(x, y + 1)))

        return get_min_hp(0, 0)
