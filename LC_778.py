import heapq
from typing import List


class Solution:
    def swimInWater(self, grid: List[List[int]]) -> int:
        n = len(grid)
        result = grid[0][0]

        visited = [[False for _ in range(n)] for _ in range(n)]
        que = [(grid[0][0], 0, 0)]
        visited[0][0] = True

        while que:
            current = heapq.heappop(que)
            time = current[0]
            x = current[1]
            y = current[2]

            result = max(result, time)

            if x == n - 1 and y == n - 1:
                return result

            for direction in [(0, 1), (1, 0), (0, -1), (-1, 0)]:
                next_x = x + direction[0]
                next_y = y + direction[1]
                if 0 <= next_x < n and 0 <= next_y < n and not visited[next_x][next_y]:
                    heapq.heappush(que, (grid[next_x][next_y], next_x, next_y))
                    visited[next_x][next_y] = True

        return result
