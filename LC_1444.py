from functools import lru_cache
from typing import List


class Solution:
    def ways(self, pizza: List[str], k: int) -> int:

        rows = len(pizza)
        cols = len(pizza[0])

        apple_dp = [[0 for _ in range(cols)] for _ in range(rows)]
        for i in range(rows):
            for j in range(cols):
                apple_dp[i][j] += apple_dp[i - 1][j] if i > 0 else 0
                apple_dp[i][j] += apple_dp[i][j - 1] if j > 0 else 0
                apple_dp[i][j] -= apple_dp[i - 1][j - 1] if i > 0 and j > 0 else 0
                apple_dp[i][j] += 1 if pizza[i][j] == 'A' else 0

        def count_apple(top, left, bottom, right):
            cnt = apple_dp[bottom][right]
            cnt -= apple_dp[bottom][left - 1] if left > 0 else 0
            cnt -= apple_dp[top - 1][right] if top > 0 else 0
            cnt += apple_dp[top - 1][left - 1] if top > 0 and left > 0 else 0
            return cnt

        @lru_cache(None)
        def solve(top: int, left: int, pieces: int):
            if pieces == 0:
                return 1 if count_apple(top, left, rows - 1, cols - 1) > 0 else 0
            if top == rows or left == cols:
                return 0

            cut_top = 0
            for i in range(top, rows):
                if count_apple(top, left, i, cols - 1) > 0:
                    cut_top += solve(i + 1, left, pieces - 1)

            cut_left = 0
            for j in range(left, cols):
                if count_apple(top, left, rows - 1, j) > 0:
                    cut_left += solve(top, j + 1, pieces - 1)

            return (cut_top + cut_left) % (10 ** 9 + 7)

        return solve(0, 0, k - 1)
