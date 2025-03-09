from typing import List
from functools import cache

class Solution:
    def isThereAPath(self, grid: List[List[int]]) -> bool:
        m = len(grid)
        n = len(grid[0])
        if (m+n-1) % 2 == 1:
            return False

        @cache
        def solve(i,j,cnt) -> bool:
            if i >= m or j >= n or i < 0 or j < 0:
                return False
            if grid[i][j] == 1:
                cnt += 1
            if i == m-1 and j == n-1:
                if cnt == (m+n-1)//2:
                    return True

            ret = solve(i+1, j, cnt) or solve(i, j+1, cnt)

            return ret

        return solve(0,0,0)