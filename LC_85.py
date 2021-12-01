from typing import List


class Solution:
    def maximalRectangle(self, matrix: List[List[str]]) -> int:
        if len(matrix) == 0:
            return 0

        ans = 0
        rows = len(matrix)
        cols = len(matrix[0])
        heights = [0] * (cols + 1)

        for i in range(rows):
            for j in range(cols):
                heights[j] = (heights[j] + 1) * int(matrix[i][j])

            # largest rectangle in histogram
            stack = [-1]
            for j in range(cols + 1):
                while heights[stack[-1]] > heights[j]:
                    width = j - stack[-2] - 1
                    height = heights[stack[-1]]
                    ans = max(ans, height * width)
                    stack.pop()

                stack.append(j)

        return ans
