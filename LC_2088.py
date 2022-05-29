class Solution:
    def countPyramids(self, grid: List[List[int]]) -> int:
        reversed_grid = []
        for i in range(len(grid) - 1, -1, -1):
            reversed_grid.append(grid[i][:])
        output = self.helper(grid)
        output += self.helper(reversed_grid)
        return output
    
    def helper(self, grid):
        output, rows, cols = 0, len(grid), len(grid[0])
        for i in range(1, rows):
            for j in range(1, cols - 1):
                if grid[i][j] and grid[i - 1][j]:
                    grid[i][j] = min(grid[i - 1][j - 1], grid[i - 1][j + 1]) + 1
                    output += grid[i][j] - 1
        return output
